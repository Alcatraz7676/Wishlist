package ru.ktsstudio.wishlist.ui

import com.arellomobile.mvp.MvpAppCompatFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : MvpAppCompatFragment() {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}