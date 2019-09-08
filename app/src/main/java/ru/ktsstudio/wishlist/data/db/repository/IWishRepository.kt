package ru.ktsstudio.wishlist.data.db.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.ktsstudio.wishlist.data.db.model.Wish

interface IWishRepository {

    fun observeFavouriteWishes(): Observable<List<Wish>>

    fun observeMyWishes(): Observable<List<Wish>>

    fun observePopularWishes(): Observable<List<Wish>>

    fun insertWishes(wishes: List<Wish>): Single<List<Long>>

    fun getWishById(id: Long): Single<Wish>

    fun changeWishFavouriteStatus(wishId: Long, favourite: Boolean): Completable

}