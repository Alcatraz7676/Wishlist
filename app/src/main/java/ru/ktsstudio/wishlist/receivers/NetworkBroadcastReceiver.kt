package ru.ktsstudio.wishlist.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

abstract class NetworkBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.extras?.getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY) == true) {
            onNetworkLostConnection()
        } else {
            onNetworkGainConnection()
        }
    }

    abstract fun onNetworkLostConnection()
    abstract fun onNetworkGainConnection()
}