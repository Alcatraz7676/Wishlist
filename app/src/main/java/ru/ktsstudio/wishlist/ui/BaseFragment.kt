package ru.ktsstudio.wishlist.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    protected val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}