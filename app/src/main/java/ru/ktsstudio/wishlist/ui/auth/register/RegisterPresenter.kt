package ru.ktsstudio.wishlist.ui.auth.register

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
class RegisterPresenter(
    private val registerInteractor: IRegisterInteractor,
    private val resources: Resources,
    private val localRouter: Router,
    private val globalRouter: Router
) : BasePresenter<RegisterView>() {

    fun register(firstName: String, lastName: String, email: String, password: String) {
        registerInteractor.register(firstName, lastName, email, password)
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnError { viewState.showLoading(false) }
            .subscribe(
                {
                    viewState.requestContactPermission()
                }, {
                    if (it is HttpStatusInterceptor.UserExistsException)
                        viewState.showToast(resources.getString(R.string.auth_fragment_toast_same_email))
                    else
                        viewState.showToast(resources.getString(R.string.auth_fragment_toast_register_error))
                }
            ).addTo(compositeDisposable)
    }

    fun showRationaleDialog(show: Boolean) {
        viewState.showPermissionRationale(show)
    }

    fun registerBtnActivation(
        emailObservable: Observable<CharSequence>,
        passwordObservable: Observable<CharSequence>,
        nameObservable: Observable<CharSequence>,
        surnameObservable: Observable<CharSequence>
    ) {
        Observables.combineLatest(
            emailObservable,
            passwordObservable,
            nameObservable,
            surnameObservable
        ) { newEmail, newPassword, newName, newSurname ->
            viewState.enableRegister(
                newEmail.isNotBlank() && newPassword.isNotBlank() &&
                        newName.isNotBlank() && newSurname.isNotBlank()
            )
        }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun navigateToMain() {
        globalRouter.navigateTo(Screens.MainContainer())
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}