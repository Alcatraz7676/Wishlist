package ru.ktsstudio.wishlist.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.ktsstudio.wishlist.data.stores.LocalWishesStore

sealed class WishAdapterModel {

    @Parcelize
    data class Wish(
        val id: Int = nextId++,
        val title: String,
        val description: String,
        val author: User? = null,
        val isFavourite: Boolean = false,
        val photoId: Int? = null
    ) : WishAdapterModel(), Parcelable

    data class Header(
        val title: String
    ) : WishAdapterModel()

    companion object {
        var nextId: Int = LocalWishesStore.getAllWishes().size
    }

}