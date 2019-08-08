package ru.ktsstudio.wishlist.ui.auth.register

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RegisterView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToMain()

}