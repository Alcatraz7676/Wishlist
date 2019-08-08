package ru.ktsstudio.wishlist.ui

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import ru.ktsstudio.wishlist.di.DI
import toothpick.Toothpick

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    open fun onCreate() {
        initDi()
    }

    private fun initDi() {
        val scope = Toothpick.openScope(DI.APP)
        Toothpick.inject(this, scope)
    }

}