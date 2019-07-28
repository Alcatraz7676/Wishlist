package ru.ktsstudio.wishlist.data.models.response

import ru.ktsstudio.wishlist.data.models.WishData

data class WishResponse (
    val status: String?,
    val data: WishData,
    val code: String?,
    val message: String?
)