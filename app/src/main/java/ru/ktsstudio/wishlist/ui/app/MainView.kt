package ru.ktsstudio.wishlist.ui.app

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSnackBar(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun registerBroadcastReciever(register: Boolean)

}