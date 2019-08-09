package ru.ktsstudio.wishlist.ui.auth.login

import android.Manifest
import android.app.Activity
import android.content.SharedPreferences
import androidx.core.app.ActivityCompat
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.data.models.body.LoginBody
import ru.ktsstudio.wishlist.data.network.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.ui.BasePresenter
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import javax.inject.Inject

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {

    @Inject
    lateinit var wishApiService: WishApiService
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var activity: Activity

    fun login(email: String, password: String) {
        wishApiService.login(
            LoginBody(email, password)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnError { viewState.showLoading(false) }
            .subscribe({ response ->
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString(KEY_TOKEN, response.data?.token)
                    putString(KEY_USER_LOGIN, response.data?.email)
                }
                editor.apply()
                requestPermissions()
            }, {
                if (it is HttpStatusInterceptor.UnauthorizedException)
                    viewState.showToast("Неверный логин или пароль")
                else
                    viewState.showToast("Не удалось авторизоваться")
            })
            .addTo(compositeDisposable)
    }

    fun showRationaleDialog(show: Boolean) {
        viewState.showPermissionRationale(show)
    }

    fun requestContactPermission() {
        viewState.requestContactPermission()
    }

    fun navigateToMain() {
        viewState.navigateToMain()
    }

    fun navigateToRegister() {
        viewState.navigateToRegister()
    }

    private fun requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.READ_CONTACTS
            )
        ) {
            viewState.showPermissionRationale(true)
        } else {
            requestContactPermission()
        }
    }

}