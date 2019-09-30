package ru.ktsstudio.wishlist.ui.main.detail

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.db.model.Wish

interface IWishDetailInteractor {

    fun getWishById(id: Long): Single<Wish>
}