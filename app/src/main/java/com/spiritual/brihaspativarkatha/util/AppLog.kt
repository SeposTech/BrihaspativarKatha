package com.spiritual.brihaspativarkatha.util

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.spiritual.brihaspativarkatha.BuildConfig

object AppLog {
    private const val TAG = "BrihaspativarApp"

    fun d(message: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(TAG, message)
        }
    }

    fun i(message: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(TAG, message)
        }
    }

    fun e(message: String, throwable: Throwable? = null) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(TAG, message, throwable)
        } else {
            // Report to Crashlytics in release builds
            val crashlytics = FirebaseCrashlytics.getInstance()
            crashlytics.log(message)
            if (throwable != null) {
                crashlytics.recordException(throwable)
            } else {
                crashlytics.recordException(Exception(message))
            }
        }
    }
}