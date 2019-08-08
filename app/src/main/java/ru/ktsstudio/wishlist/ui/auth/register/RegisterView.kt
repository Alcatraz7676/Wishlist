package ru.ktsstudio.wishlist.ui.auth.register

import com.arellomobile.mvp.MvpView

interface RegisterView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    fun navigateToMain()

}