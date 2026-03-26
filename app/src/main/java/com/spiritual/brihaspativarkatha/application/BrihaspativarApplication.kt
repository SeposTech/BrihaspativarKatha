package com.spiritual.brihaspativarkatha.application

import android.app.Application
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

class BrihaspativarApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AnalyticsHelper.init(this)
    }
}