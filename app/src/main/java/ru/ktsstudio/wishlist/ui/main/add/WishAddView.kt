package ru.ktsstudio.wishlist.ui.main.add

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface WishAddView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun enableAdd(enable: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(toLoad: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearEditText()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String?)

}