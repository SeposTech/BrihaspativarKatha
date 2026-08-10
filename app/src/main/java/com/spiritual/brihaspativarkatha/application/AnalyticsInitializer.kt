package com.spiritual.brihaspativarkatha.application

import androidx.startup.Initializer
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

class AnalyticsInitializer : Initializer<Unit> {
    override fun create(context: android.content.Context) {
        AnalyticsHelper.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return emptyList()
    }
}