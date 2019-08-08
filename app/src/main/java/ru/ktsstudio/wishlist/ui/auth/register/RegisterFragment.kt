package ru.ktsstudio.wishlist.ui.auth.register

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.fragment_register.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.app.WishApp
import ru.ktsstudio.wishlist.data.models.body.RegisterBody
import ru.ktsstudio.wishlist.data.network.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.auth.AuthNavigator
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import toothpick.Toothpick
import javax.inject.Inject

class RegisterFragment : BaseFragment() {

    @Inject
    lateinit var wishApiService: WishApiService
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val authNavigator: AuthNavigator
        get() = parentFragment as AuthNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, Toothpick.openScopes(DI.APP, DI.ACTIVITY))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_register.clicks().subscribe {
            register()
        }.addTo(compositeDisposable)
    }

    private fun setupEditText() {
        val emailObservable = input_email.textChanges()
        val passwordObservable = input_password.textChanges()
        val nameObservable = input_name.textChanges()
        val surnameObservable = input_surname.textChanges()

        Observables.combineLatest(emailObservable, passwordObservable, nameObservable, surnameObservable) { newEmail, newPassword, newName, newSurname ->
            btn_register.isEnabled = newEmail.isNotBlank() && newPassword.isNotBlank() &&
                    newName.isNotBlank() && newSurname.isNotBlank()
        }.subscribe()
    }

    private fun register() {
        wishApiService.register(
                RegisterBody(
                        firstName = input_name.text.toString(),
                        lastName = input_surname.text.toString(),
                        email = input_email.text.toString(),
                        password = input_password.text.toString()
                )
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading(true) }
                .doOnError { showLoading(false) }
                .subscribe({ response ->
                    sharedPreferences.edit().apply {
                        putString(KEY_TOKEN, response.data?.token)
                        putString(KEY_USER_LOGIN, response.data?.email)
                    }.apply()
                    authNavigator.navigateToMain()
                }, {
                    if (it is HttpStatusInterceptor.UserExistsException)
                        showToast("Пользователь с такой почтой уже существует")
                    else
                        showToast("Не удалось авторизоваться")
                })
                .addTo(compositeDisposable)
    }

    private fun showLoading(toLoad: Boolean) {
        group_register.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

}