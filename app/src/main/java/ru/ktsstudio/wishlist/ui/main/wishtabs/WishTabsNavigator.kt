package ru.ktsstudio.wishlist.ui.main.wishtabs

import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

interface WishTabsNavigator {
    fun navigateToWishDetail(wish: Wish)
}