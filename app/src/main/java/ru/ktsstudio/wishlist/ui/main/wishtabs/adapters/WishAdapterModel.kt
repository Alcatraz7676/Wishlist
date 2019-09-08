package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

sealed class WishAdapterModel {

    data class Wish(
            val id: Long,
            val title: String,
            val description: String,
            val authorLogin: String,
            val isFavourite: Boolean = false,
            val photoId: Int
    ) : WishAdapterModel()

    data class Header(
            val title: String
    ) : WishAdapterModel()

}