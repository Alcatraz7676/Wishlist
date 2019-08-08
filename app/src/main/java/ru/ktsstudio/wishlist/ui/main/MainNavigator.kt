package ru.ktsstudio.wishlist.ui.main

import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

interface MainNavigator {

    fun navigateToWishTabs()

    fun navigateToWishAdd()

    fun navigateToWishDetail(wish: Wish)

    fun navigateToLogin()

}