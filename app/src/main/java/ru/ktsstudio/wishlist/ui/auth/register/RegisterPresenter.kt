package ru.ktsstudio.wishlist.ui.auth.register

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.data.models.body.RegisterBody
import ru.ktsstudio.wishlist.data.network.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.ui.BasePresenter
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import javax.inject.Inject

@InjectViewState
class RegisterPresenter : BasePresenter<RegisterView>() {

    @Inject
    lateinit var wishApiService: WishApiService
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    fun register(registerBody: RegisterBody) {
        wishApiService.register(registerBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnError { viewState.showLoading(false) }
            .subscribe({ response ->
                sharedPreferences.edit().apply {
                    putString(KEY_TOKEN, response.data?.token)
                    putString(KEY_USER_LOGIN, response.data?.email)
                }.apply()
                viewState.navigateToMain()
            }, {
                if (it is HttpStatusInterceptor.UserExistsException)
                    viewState.showToast("Пользователь с такой почтой уже существует")
                else
                    viewState.showToast("Не удалось авторизоваться")
            })
            .addTo(compositeDisposable)
    }

}