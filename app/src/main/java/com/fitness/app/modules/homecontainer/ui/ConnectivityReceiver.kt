package com.fitness.app.modules.homecontainer.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityReceiver(private val connectivityListener: ConnectivityListener) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        connectivityListener.onNetworkConnectionChanged(isConnected)
    }

    interface ConnectivityListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }
}