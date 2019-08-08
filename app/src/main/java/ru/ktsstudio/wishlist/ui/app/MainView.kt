package ru.ktsstudio.wishlist.ui.app

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun showSnackBar(show: Boolean)

    fun navigateToLoginScreen()

    fun navigateToMainScreen()

}