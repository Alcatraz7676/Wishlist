package ru.ktsstudio.wishlist.ui.main.wishtabs

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

interface WishTabsView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToWishAdd()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToWishDetail(wish: Wish)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToLogin()

}