package com.spiritual.brihaspativarkatha.application

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

class BrihaspativarApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AnalyticsHelper.init(this)
        MobileAds.initialize(this)
    }
}