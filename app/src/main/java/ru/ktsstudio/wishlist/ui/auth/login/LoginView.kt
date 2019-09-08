package ru.ktsstudio.wishlist.ui.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(loading: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPermissionRationale(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun requestContactPermission()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun enableLogin(enable: Boolean)

}