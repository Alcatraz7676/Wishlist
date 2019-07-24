package ru.ktsstudio.wishlist.data.models.body

data class RegisterBody (
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val email: String,
    val password: String = ""
)