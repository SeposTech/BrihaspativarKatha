package com.spiritual.brihaspativarkatha.application

import androidx.startup.Initializer
import com.google.android.gms.ads.MobileAds

class AdMobInitializer : Initializer<Unit> {
    override fun create(context: android.content.Context) {
        MobileAds.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}