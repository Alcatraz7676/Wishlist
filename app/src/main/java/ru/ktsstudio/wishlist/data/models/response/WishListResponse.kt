package ru.ktsstudio.wishlist.data.models.response

import ru.ktsstudio.wishlist.data.models.WishData

data class WishListResponse (
    val status: String?,
    val data: List<WishData>,
    val code: String?,
    val message: String?
)