package com.spiritual.brihaspativarkatha.data.analytics

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory
import com.spiritual.brihaspativarkatha.util.AppLog

class InAppReviewManager(
    private val context: Context
) {

    fun launchReviewFlow(activity: Activity) {

        AppLog.d("launchReviewFlow() called")

        val manager = ReviewManagerFactory.create(context)

        manager.requestReviewFlow()
            .addOnCompleteListener { request ->

                AppLog.d("requestReviewFlow completed")

                if (request.isSuccessful) {

                    AppLog.d("requestReviewFlow SUCCESS")

                    val reviewInfo = request.result

                    manager.launchReviewFlow(
                        activity,
                        reviewInfo
                    ).addOnCompleteListener {

                        AppLog.d(
                            "launchReviewFlow completed (dialog may or may not have been shown)"
                        )
                    }

                } else {

                    AppLog.e(
                        "requestReviewFlow FAILED ${request.exception?.message}"
                    )
                }
            }
    }
}