package ru.ktsstudio.wishlist.ui.main.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.ktsstudio.wishlist.data.db.model.Wish

interface WishDetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWish(wish: Wish)

}