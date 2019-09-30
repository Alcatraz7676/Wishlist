package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel
import ru.ktsstudio.wishlist.utils.Screens
import ru.ktsstudio.wishlist.utils.toAdapterModel
import ru.terrakok.cicerone.Router

@InjectViewState
class TabPresenter(
    private val tabInteractor: ITabInteractor,
    private val localRouter: Router,
    private val wishesObservable: Observable<List<Wish>>
) : BasePresenter<TabView>() {

    fun addWishToFavourite(wishId: Long, favourite: Boolean) {
        tabInteractor.changeWishFavouriteStatus(wishId, favourite)
            .subscribe {
                Log.d("TabFragment", "Wish успешно добавлен в избранное")
            }.addTo(compositeDisposable)
    }

    fun getItems() {
        tabInteractor.getContactNames()
            .doFinally { subscribeOnDbChanges() }
            .subscribe(
                {
                    viewState.setContactNames(it)
                }, {
                    Log.d("TabFragment", it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    fun shuffleItems(wishes: List<WishAdapterModel.Wish>) {
        viewState.setWishes(wishes.shuffled())
    }

    fun navigateToWishDetail(id: Long) {
        localRouter.navigateTo(Screens.WishDetailScreen(id))
    }

    private fun subscribeOnDbChanges() {
        wishesObservable
            .subscribe(
                { wishes ->
                    viewState.setWishes(wishes.map { it.toAdapterModel() })
                }, {
                    Log.d("TabPresenter onError", it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

}