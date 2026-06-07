package com.spiritual.brihaspativarkatha.worker

import android.content.Context
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit

class AartiScheduler {

    fun schedule(context: Context) {

        // 🔥 PRODUCTION CODE (6 AM delay)
        val request = OneTimeWorkRequestBuilder<AartiWorker>()

            // 🧪 TEST MODE (temporary)
            // 👉 5 sec me notification test ke liye uncomment karo
            // .setInitialDelay(5, TimeUnit.SECONDS)

            // 🧪 REAL MODE (production)
            .setInitialDelay(calculateDelay(), TimeUnit.MILLISECONDS)

            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "daily_aarti",
                ExistingWorkPolicy.REPLACE,
                request
            )
    }

    private fun calculateDelay(): Long {

        val now = Calendar.getInstance()

        val target = Calendar.getInstance().apply {

            // ⏰ PRODUCTION TIME (6:00 AM)
            /*set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)*/

            // 🧪 TEST MODE OPTION:
            // 👉 next 1 minute test ke liye uncomment karo
             set(Calendar.MINUTE, now.get(Calendar.MINUTE) + 1)
        }

        if (now.after(target)) {
            target.add(Calendar.DAY_OF_MONTH, 1)
        }

        return target.timeInMillis - now.timeInMillis
    }
}