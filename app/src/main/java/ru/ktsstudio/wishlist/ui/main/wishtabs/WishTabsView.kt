package ru.ktsstudio.wishlist.ui.main.wishtabs

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

interface WishTabsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun navigateToWishAdd()

    @StateStrategyType(SkipStrategy::class)
    fun navigateToWishDetail(wish: Wish)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToLogin()

}