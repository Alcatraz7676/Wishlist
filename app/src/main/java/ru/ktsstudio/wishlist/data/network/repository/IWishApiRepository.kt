package ru.ktsstudio.wishlist.data.network.repository

import io.reactivex.Single
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.data.network.model.response.AuthResponse
import ru.ktsstudio.wishlist.data.network.model.response.WishAddResponse

interface IWishApiRepository {

    fun login(email: String, password: String): Single<AuthResponse>

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Single<AuthResponse>

    fun addWish(title: String, description: String): Single<WishAddResponse>

    fun loadWishesFromServer(insertCallback: (List<Wish>) -> Single<List<Long>>)

}