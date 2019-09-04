package ru.ktsstudio.wishlist.ui.app

import com.arellomobile.mvp.InjectViewState
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import ru.ktsstudio.wishlist.ui.common.BasePresenter
import ru.ktsstudio.wishlist.utils.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val router: Router
) : BasePresenter<MainView>() {

    fun checkLoginState() {
        val userLogin = sharedPreferenceRepository.getCurrentUserLogin()
        if (userLogin == null) {
            router.navigateTo(Screens.AuthContainer())
        } else {
            router.navigateTo(Screens.MainContainer())
        }
    }

    fun showSnackBar(show: Boolean) {
        viewState.showSnackBar(show)
    }

    fun observeConnectivity(enabled: Boolean) {
        viewState.registerBroadcastReciever(enabled)
    }

}