package ru.ktsstudio.wishlist.ui.main.wishtabs

import com.arellomobile.mvp.MvpView
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

interface WishTabsView : MvpView {

    fun navigateToWishAdd()

    fun navigateToWishDetail(wish: Wish)

    fun navigateToLogin()

}