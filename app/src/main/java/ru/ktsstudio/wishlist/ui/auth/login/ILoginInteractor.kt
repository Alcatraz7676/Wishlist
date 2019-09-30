package ru.ktsstudio.wishlist.ui.auth.login

import io.reactivex.Single

interface ILoginInteractor {

    fun login(email: String, password: String): Single<Unit>
}