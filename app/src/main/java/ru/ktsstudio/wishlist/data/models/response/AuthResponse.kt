package ru.ktsstudio.wishlist.data.models.response

import ru.ktsstudio.wishlist.data.models.AuthData

data class AuthResponse(
    val status: String?,
    val data: AuthData?,
    val code: String?,
    val message: String?
)