package ru.ktsstudio.wishlist.ui.app

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.receivers.NetworkBroadcastReceiver
import ru.ktsstudio.wishlist.ui.auth.AuthFragmentContainer
import ru.ktsstudio.wishlist.ui.main.MainFragmentContainer

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
        setupSnackbar()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun navigateToMainScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, MainFragmentContainer())
            .commitNow()
    }

    override fun navigateToLoginScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, AuthFragmentContainer())
            .commitNow()
    }

    private fun setupSnackbar() {
        val view = findViewById<View>(R.id.activity_content)
        snackbar = Snackbar.make(view, R.string.snackbar_network, Snackbar.LENGTH_INDEFINITE)
    }

}