package ru.ktsstudio.wishlist.ui.main.add

import android.content.res.Resources
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class WishAddPresenter(
    private val wishAddInteractor: IWishAddInteractor,
    private val resources: Resources,
    private val localRouter: Router
) : BasePresenter<WishAddView>() {

    fun addWish(title: String, description: String) {
        wishAddInteractor.addWish(title, description)
            .doOnSubscribe { viewState.showLoading(true) }
            .doOnSuccess { viewState.clearEditText() }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe({
                viewState.showToast(resources.getString(R.string.wishadd_fragment_toast_success))
            }, {
                viewState.showToast(resources.getString(R.string.wishadd_fragment_toast_failed))
            }).addTo(compositeDisposable)
    }

    fun enableBtnActivation(
        titleObservable: Observable<CharSequence>,
        descriptionObservable: Observable<CharSequence>
    ) {
        Observables.combineLatest(titleObservable, descriptionObservable) { title, description ->
            viewState.enableAdd(title.isNotBlank() && description.isNotBlank())
        }.subscribe()
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}