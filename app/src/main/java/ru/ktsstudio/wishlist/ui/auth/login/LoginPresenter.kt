package ru.ktsstudio.wishlist.ui.auth.login

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.network.interceptors.HttpStatusInterceptor
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.utils.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class LoginPresenter(
    private val loginInteractor: ILoginInteractor,
    private val resources: Resources,
    private val localRouter: Router,
    private val globalRouter: Router
) : BasePresenter<LoginView>() {

    fun login(email: String, password: String) {
        loginInteractor.login(email, password)
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnError { viewState.showLoading(false) }
            .subscribe({
                viewState.requestContactPermission()
            }, {
                if (it is HttpStatusInterceptor.UnauthorizedException)
                    viewState.showToast(resources.getString(R.string.auth_fragment_toast_wrong_login_or_password))
                else
                    viewState.showToast(resources.getString(R.string.auth_fragment_toast_auth_error))
            })
            .addTo(compositeDisposable)
    }

    fun showRationaleDialog(show: Boolean) {
        viewState.showPermissionRationale(show)
    }

    fun loginBtnActivation(
        emailObservable: Observable<CharSequence>,
        passwordObservable: Observable<CharSequence>
    ) {
        Observables.combineLatest(emailObservable, passwordObservable) { newEmail, newPassword ->
            viewState.enableLogin(newEmail.isNotBlank() && newPassword.isNotBlank())
        }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun navigateToMain() {
        globalRouter.newRootScreen(Screens.MainContainer())
    }

    fun navigateToRegister() {
        localRouter.navigateTo(Screens.RegisterScreen())
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}