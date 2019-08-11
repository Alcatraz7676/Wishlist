package ru.ktsstudio.wishlist.ui.app

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface MainView : MvpView {

    fun showSnackBar(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToLoginScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToLoginScreenAnimation()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMainScreen()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMainScreenAnimation()

}