package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel

interface TabView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContactNames(contactNames: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setWishes(wishes: List<WishAdapterModel.Wish>)

}