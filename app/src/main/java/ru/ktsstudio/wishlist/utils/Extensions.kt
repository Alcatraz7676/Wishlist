package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.FragmentTransaction
import ru.ktsstudio.wishlist.data.db.model.Wish
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel

fun FragmentTransaction.setFadeAnimation() {
    this.setCustomAnimations(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )
}

fun Wish.toAdapterModel() = WishAdapterModel.Wish(
        this.id,
        this.title,
        this.description,
        this.author.login,
        this.isFavourite,
        this.photoId
    )