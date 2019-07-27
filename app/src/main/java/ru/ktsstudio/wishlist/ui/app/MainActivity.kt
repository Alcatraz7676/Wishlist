package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.receivers.NetworkBroadcastReceiver
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.ui.OnBackPressed
import ru.ktsstudio.wishlist.utils.navigateReplace

class MainActivity : AppCompatActivity(), ActivityNavigator {

    private var snackbar: Snackbar? = null
    var currentUser: User? = null

    private val receiver = object : NetworkBroadcastReceiver() {

        override fun onNetworkLostConnection() {
            snackbar = activity_content.makeSnackbar()
            snackbar?.show()
        }

        override fun onNetworkGainConnection() {
            snackbar?.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToLoginScreen()
        }
    }

    @Suppress("DEPRECATION")
    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun navigateToMainScreen() {
        supportFragmentManager.navigateReplace(R.id.activity_content, MainFragmentContainer())
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.navigateReplace(R.id.activity_content, AuthFragmentContainer())
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.activity_content)
        if (!(fragment as OnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }
    }

    private fun View.makeSnackbar() =
        Snackbar.make(this, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)

}