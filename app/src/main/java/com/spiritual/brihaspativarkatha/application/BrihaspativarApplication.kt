package com.spiritual.brihaspativarkatha.application

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.spiritual.brihaspativarkatha.BuildConfig
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

class BrihaspativarApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AnalyticsHelper.init(this)
        MobileAds.initialize(this)
        // Disable Crashlytics in debug builds to avoid polluting crash reports
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }
}