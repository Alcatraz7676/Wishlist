package ru.ktsstudio.wishlist.data.network.model.response

data class AuthData (
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val email: String = "",
    val token: String?,
    val id: String?
)