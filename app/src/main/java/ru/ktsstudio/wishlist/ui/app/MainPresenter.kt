package ru.ktsstudio.wishlist.ui.app

import android.app.Application
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.arellomobile.mvp.InjectViewState
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers
import io.reactivex.rxkotlin.addTo
import ru.ktsstudio.wishlist.ui.BasePresenter
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN
import javax.inject.Inject

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var application: Application

    override fun onStart() {
        super.onStart()
        registerBroadcast()
    }

    fun checkLoginState() {
        val userLogin = sharedPreferences.getString(KEY_USER_LOGIN, null)
        if (userLogin == null) {
            viewState.navigateToLoginScreen()
        } else {
            viewState.navigateToMainScreen()
        }
    }

    fun showSnackBar(show: Boolean) {
        viewState.showSnackBar(show)
    }

    private fun registerBroadcast() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        RxBroadcastReceivers.fromIntentFilter(application.applicationContext, filter)
            .subscribe { intent ->
                if (intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) == true) {
                    showSnackBar(true)
                } else {
                    showSnackBar(false)
                }
            }.addTo(compositeDisposable)
    }

}