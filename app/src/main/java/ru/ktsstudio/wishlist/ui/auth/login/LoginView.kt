package ru.ktsstudio.wishlist.ui.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    fun showPermissionRationale(show: Boolean)
    
    fun requestContactPermission()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMain()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToRegister()

}