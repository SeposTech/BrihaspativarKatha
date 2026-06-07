package com.spiritual.brihaspativarkatha.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.spiritual.brihaspativarkatha.R
import com.spiritual.brihaspativarkatha.notification.NotificationHelper
import java.util.*

class AartiWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        return try {

            val (title, message, soundRes, deepLink) = getTodayData()

            NotificationHelper.showNotification(
                applicationContext,
                title,
                message,
                soundRes,
                deepLink
            )

            Result.success()

        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun getTodayData(): Quad<String, String, Int, String> {

        val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        return when (day) {

            Calendar.MONDAY -> Quad(
                "Somvaar",
                "Shiv ji ki aarti karein",
                R.raw.monday,
                "app://shiv"
            )

            Calendar.TUESDAY -> Quad(
                "Mangalvaar",
                "Hanuman Chalisa padhein",
                R.raw.tuesday,
                "app://hanuman"
            )

            Calendar.WEDNESDAY -> Quad(
                "Budhvaar",
                "Ganesh ji ki aarti karein",
                R.raw.wedensday,
                "app://ganesh"
            )

            Calendar.THURSDAY -> Quad(
                "Guruvaar",
                "Brihaspati Dev ki aarti karein",
                R.raw.thrusday,
                "app://guru"
            )

            Calendar.FRIDAY -> Quad(
                "Shukravaar",
                "Maa Lakshmi ki aarti karein",
                R.raw.friday,
                "app://lakshmi"
            )

            Calendar.SATURDAY -> Quad(
                "Shanivaar",
                "Shani Dev ya Hanuman Chalisa",
                R.raw.saturday,
                "app://shani"
            )

            else -> Quad(
                "Ravivaar",
                "Surya Dev ki aarti karein",
                R.raw.sunday,
                "app://surya"
            )
        }
    }
}

/**
 * Simple container (no extra dependency)
 */
data class Quad<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)