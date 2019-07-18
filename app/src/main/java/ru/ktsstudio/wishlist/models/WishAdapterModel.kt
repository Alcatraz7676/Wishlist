package ru.ktsstudio.wishlist.models

sealed class WishAdapterModel {

    data class Wish(
        val id: Int,
        val title: String,
        val description: String,
        val author: User,
        val isFavorite: Boolean
    ) : WishAdapterModel()

    data class Header(
        val title: String
    ) : WishAdapterModel()
}