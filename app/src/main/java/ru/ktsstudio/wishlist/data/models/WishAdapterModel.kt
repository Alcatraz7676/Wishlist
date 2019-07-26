package ru.ktsstudio.wishlist.data.models

sealed class WishAdapterModel {

    data class Wish(
        val id: Int = nextId++,
        val title: String,
        val description: String,
        val author: User? = null,
        val isFavourite: Boolean = false,
        val photoId: Int? = null
    ) : WishAdapterModel()

    data class Header(
        val title: String
    ) : WishAdapterModel()

    companion object {
        var nextId: Int = 0
    }

}