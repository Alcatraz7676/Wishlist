package ru.ktsstudio.wishlist.data.network.model.response

data class WishAddResponse (
    val status: String?,
    val data: WishData,
    val code: String?,
    val message: String?
)