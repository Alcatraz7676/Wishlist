package ru.ktsstudio.wishlist.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.WishApp
import ru.ktsstudio.wishlist.data.models.body.LoginBody
import ru.ktsstudio.wishlist.data.network.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.auth.AuthNavigator
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN

class LoginFragment : BaseFragment() {

    private val authNavigator: AuthNavigator
        get() = parentFragment as AuthNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        btn_login.clicks().subscribe {
            login(input_email.text.toString(), input_password.text.toString())
        }.addTo(compositeDisposable)
        btn_register.clicks().subscribe {
            authNavigator.navigateToRegister()
        }.addTo(compositeDisposable)
    }

    private fun setupEditText() {
        val emailObservable = input_email.textChanges()
        val passwordObservable = input_password.textChanges()

        Observables.combineLatest(emailObservable, passwordObservable) {
                newEmail, newPassword -> btn_login.isEnabled = newEmail.isNotBlank() && newPassword.isNotBlank()
        }.subscribe()
    }

    private fun login(email: String, password: String) {
        RetrofitStore.service.login(
            LoginBody(email, password)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doOnTerminate { showLoading(false) }
            .subscribe({ response ->
                val editor = WishApp.sharedPreferences.edit()
                editor.apply {
                    putString(KEY_TOKEN, response.data?.token)
                    putString(KEY_USER_LOGIN, response.data?.email)
                }
                editor.apply()
                authNavigator.navigateToMain()
            }, {
                if (it is HttpStatusInterceptor.UnauthorizedException)
                    showToast("Неверный логин или пароль")
                else
                    showToast("Не удалось авторизоваться")
            })
            .addTo(compositeDisposable)
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    private fun showLoading(toLoad: Boolean) {
        group_login.isVisible = !toLoad
        progress_bar.isVisible = toLoad
    }

}