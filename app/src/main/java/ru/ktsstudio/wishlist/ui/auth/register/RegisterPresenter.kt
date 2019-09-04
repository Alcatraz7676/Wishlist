package ru.ktsstudio.wishlist.ui.auth.register

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.network.interceptors.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.network.repository.WishApiRepository
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.utils.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class RegisterPresenter(
    private val wishApiRepository: WishApiRepository,
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val resources: Resources,
    private val localRouter: Router,
    private val globalRouter: Router
) : BasePresenter<RegisterView>() {

    fun register(firstName: String, lastName: String, email: String, password: String) {
        wishApiRepository.register(firstName, lastName, email, password)
            .map { response ->
                sharedPreferenceRepository.saveUser(response.data?.token, response.data?.email)
            }
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnError { viewState.showLoading(false) }
            .subscribe({
                navigateToMain()
            }, {
                if (it is HttpStatusInterceptor.UserExistsException)
                    viewState.showToast(resources.getString(R.string.auth_fragment_toast_same_email))
                else
                    viewState.showToast(resources.getString(R.string.auth_fragment_toast_register_error))
            }).addTo(compositeDisposable)
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