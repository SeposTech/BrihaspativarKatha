package com.spiritual.brihaspativarkatha.util

import com.spiritual.brihaspativarkatha.BuildConfig

object AppLog {
    private const val TAG = "InAppReview"

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

    fun e(message: String) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(TAG, message)
        }
    }
}