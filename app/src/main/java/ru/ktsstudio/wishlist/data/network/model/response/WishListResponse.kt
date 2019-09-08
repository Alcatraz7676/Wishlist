package ru.ktsstudio.wishlist.data.network.model.response

data class WishListResponse (
    val status: String?,
    val data: List<WishData>,
    val code: String?,
    val message: String?
)