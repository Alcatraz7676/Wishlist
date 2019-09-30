package ru.ktsstudio.wishlist.ui.main.wishtabs

import com.arellomobile.mvp.InjectViewState
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.utils.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class WishTabsPresenter(
    private val wishTabsInteractor: IWishTabsInteractor,
    private val localRouter: Router,
    private val globalRouter: Router
) : BasePresenter<WishTabsView>() {

    fun logout() {
        wishTabsInteractor.logout()
        globalRouter.newRootScreen(Screens.AuthContainer())
    }

    fun navigateToWishAdd() {
        localRouter.navigateTo(Screens.WishAddScreen())
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}