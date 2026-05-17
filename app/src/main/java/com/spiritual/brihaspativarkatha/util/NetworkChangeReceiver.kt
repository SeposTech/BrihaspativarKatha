package com.spiritual.brihaspativarkatha.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkChangeReceiver(
    private val onNetworkChange: (Boolean) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {

        context?.let {

            onNetworkChange(
                isInternetAvailable(it)
            )
        }
    }

    companion object {

        fun isInternetAvailable(
            context: Context
        ): Boolean {

            val connectivityManager =
                context.getSystemService(
                    Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager

            val network =
                connectivityManager.activeNetwork
                    ?: return false

            val capabilities =
                connectivityManager
                    .getNetworkCapabilities(network)
                    ?: return false

            return capabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
        }
    }
}