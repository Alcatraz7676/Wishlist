package ru.ktsstudio.wishlist.ui.auth.register

import io.reactivex.Single

interface IRegisterInteractor {

    fun register(firstName: String, lastName: String, email: String, password: String): Single<Unit>
}