package ru.ktsstudio.wishlist.ui.main.wishtabs.add

import com.arellomobile.mvp.MvpView

interface WishAddView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun clearEditText()

    fun showToast(text: String?)

}