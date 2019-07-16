package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.receivers.NetworkBroadcastReceiver
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer
import ru.ktsstudio.wishlist.utils.navigate

class MainActivity : AppCompatActivity(), ActivityNavigator {

    private lateinit var snackbar: Snackbar

    private val receiver = object : NetworkBroadcastReceiver() {

        override fun onNetworkLostConnection() {
            snackbar.show()
        }

        override fun onNetworkGainConnection() {
            snackbar.dismiss()
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
        snackbar = activity_content.makeSnackbar()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun navigateToMainScreen() {
        supportFragmentManager.navigate(R.id.activity_content, MainFragmentContainer.newInstance())
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.navigate(R.id.activity_content, AuthFragmentContainer.newInstance())
    }

    private fun View.makeSnackbar() =
        Snackbar.make(this, R.string.main_activity_snackbar_network_missing, Snackbar.LENGTH_INDEFINITE)

}