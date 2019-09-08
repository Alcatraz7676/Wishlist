package ru.ktsstudio.wishlist.data.network.model.body

data class RegisterBody (
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val email: String,
    val password: String = ""
)