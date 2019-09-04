package ru.ktsstudio.wishlist.data.network.model.response

data class AuthResponse(
    val status: String?,
    val data: AuthData?,
    val code: String?,
    val message: String?
)