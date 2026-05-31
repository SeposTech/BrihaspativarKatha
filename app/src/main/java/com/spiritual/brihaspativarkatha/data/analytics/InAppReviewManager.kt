package com.spiritual.brihaspativarkatha.data.analytics
import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory

class InAppReviewManager(
    private val context: Context
) {

    fun launchReviewFlow(activity: Activity) {

        val manager = ReviewManagerFactory.create(context)

        manager.requestReviewFlow()
            .addOnCompleteListener { request ->

                if (request.isSuccessful) {

                    val reviewInfo = request.result

                    manager.launchReviewFlow(
                        activity,
                        reviewInfo
                    ).addOnCompleteListener {
                        // Review flow finished
                    }
                }
            }
    }
}