package ru.ktsstudio.wishlist.data.models

sealed class WishAdapterModel {

    data class Wish(
        val id: Int = 0,
        val title: String,
        val description: String,
        val author: User? = null,
        val isFavourite: Boolean = false,
        val photoId: Int? = null
    ) : WishAdapterModel()

    data class Header(
        val title: String
    ) : WishAdapterModel()

}