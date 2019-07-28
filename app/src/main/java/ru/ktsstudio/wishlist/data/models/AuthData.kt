package ru.ktsstudio.wishlist.data.models

data class AuthData (
    val firstName: String?,
    val lastName: String?,
    val photo: String?,
    val email: String = "",
    val token: String?,
    val id: String?
)