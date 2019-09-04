package ru.ktsstudio.wishlist.ui.main.wishtabs

import com.arellomobile.mvp.InjectViewState
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.utils.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class WishTabsPresenter(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val localRouter: Router,
    private val globalRouter: Router
) : BasePresenter<WishTabsView>() {

    fun logout() {
        sharedPreferenceRepository.clearUser()
        globalRouter.newRootScreen(Screens.AuthContainer())
    }

    fun navigateToWishAdd() {
        localRouter.navigateTo(Screens.WishAddScreen())
    }

    fun onBackPressed() {
        localRouter.exit()
    }

}