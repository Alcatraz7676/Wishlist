package ru.ktsstudio.wishlist.ui.main.detail

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class WishDetailPresenter(
    private val wishDetailInteractor: IWishDetailInteractor,
    private val localRouter: Router
) : BasePresenter<WishDetailView>() {

    fun showWish(id: Long) {
        wishDetailInteractor.getWishById(id)
            .subscribe({
                viewState.setWish(it)
            }, {
                Log.e(WishDetailPresenter::class.java.name, it.localizedMessage, it)
            }).addTo(compositeDisposable)
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}