package ru.ktsstudio.wishlist.ui.main.wishtabs.add

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.body.AddBody
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.ui.BasePresenter
import javax.inject.Inject

@InjectViewState
class WishAddPresenter : BasePresenter<WishAddView>() {

    @Inject
    lateinit var wishApiService: WishApiService
    @Inject
    lateinit var application: Application

    fun addWish(title: String, description: String) {
        wishApiService.addWish(AddBody(title, description))
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showLoading(true) }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                viewState.apply {
                    clearEditText()
                    showLoading(false)
                }
            }
            .subscribe({
                viewState.showToast(application.applicationContext.getString(R.string.wishadd_fragment_toast_success))
            }, {
                viewState.showToast(application.applicationContext.getString(R.string.wishadd_fragment_toast_failed))
            }).addTo(compositeDisposable)
    }

}