package ru.ktsstudio.wishlist.ui.auth.login

import com.arellomobile.mvp.MvpView

interface LoginView : MvpView {

    fun showLoading(toLoad: Boolean)

    fun showToast(text: String)

    fun requestPermissions()

    fun navigateToMain()

    fun navigateToRegister()

}