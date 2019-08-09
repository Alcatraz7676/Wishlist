package ru.ktsstudio.wishlist.ui.auth.register

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RegisterView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMain()

}