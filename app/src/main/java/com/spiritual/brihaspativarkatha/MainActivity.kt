package com.spiritual.brihaspativarkatha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.spiritual.brihaspativarkatha.navigation.Navigation

class MainActivity : ComponentActivity() {
    lateinit var appUpdateManager: AppUpdateManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appUpdateManager = AppUpdateManagerFactory.create(this)

        setContent {
            Navigation(appUpdateManager)
        }
    }
}