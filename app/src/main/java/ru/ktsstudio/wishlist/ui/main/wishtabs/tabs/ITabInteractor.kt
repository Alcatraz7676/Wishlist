package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import ru.ktsstudio.wishlist.data.db.model.Wish

interface ITabInteractor {

    fun changeWishFavouriteStatus(wishId: Long, favourite: Boolean): Completable

    fun getContactNames(): Maybe<List<String>>

    fun observeFavouriteWishes(): Observable<List<Wish>>

    fun observeMyWishes(): Observable<List<Wish>>

    fun observePopularWishes(): Observable<List<Wish>>
}