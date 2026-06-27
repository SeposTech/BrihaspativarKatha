package com.spiritual.brihaspativarkatha

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.spiritual.brihaspativarkatha.navigation.Navigation
import com.spiritual.brihaspativarkatha.screen.NoInternetScreen
import com.spiritual.brihaspativarkatha.util.NetworkChangeReceiver

class MainActivity : ComponentActivity() {
    lateinit var appUpdateManager: AppUpdateManager
    private var isConnected by mutableStateOf(true)

    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink()
        appUpdateManager = AppUpdateManagerFactory.create(this)
        networkChangeReceiver = NetworkChangeReceiver { connected ->
            isConnected = connected
        }
        registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        setContent {
            if (isConnected) {

                Navigation(appUpdateManager)

            } else {

                NoInternetScreen(
                    onRetryClick = {
                        // retry logic optional
                    }
                )
            }

        }
    }

    private fun handleDeepLink() {

        val link = intent.getStringExtra("deep_link")

        when (link) {

            "app://shiv" -> {}
            "app://hanuman" -> {}
            "app://ganesh" -> {}
            "app://guru" -> {}
        }
    }
}