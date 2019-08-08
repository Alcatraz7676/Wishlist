package ru.ktsstudio.wishlist.ui.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    fun requestPermissions()

    @StateStrategyType(SkipStrategy::class)
    fun navigateToMain()

    @StateStrategyType(SkipStrategy::class)
    fun navigateToRegister()

}