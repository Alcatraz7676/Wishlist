package ru.ktsstudio.wishlist.ui.auth.register

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface RegisterView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(toLoad: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun enableRegister(enable: Boolean)

}