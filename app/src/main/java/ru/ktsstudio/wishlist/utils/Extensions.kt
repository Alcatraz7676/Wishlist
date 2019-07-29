package ru.ktsstudio.wishlist.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun FragmentManager.navigateReplace(containerId: Int, fragment: Fragment) {
    this.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}

fun FragmentManager.navigateAdd(containerId: Int, fragment: Fragment, tag: String? = null) {
    this.beginTransaction()
        .addToBackStack(tag)
        .replace(containerId, fragment)
        .commit()
}

fun Disposable.addTo(composite: CompositeDisposable) {
    composite.addAll(this)
}