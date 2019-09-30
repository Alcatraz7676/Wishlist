package ru.ktsstudio.wishlist.ui.main.add

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.network.model.response.WishAddResponse

interface IWishAddInteractor {

    fun addWish(title: String, description: String): Single<WishAddResponse>
}