package com.spiritual.brihaspativarkatha.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.spiritual.brihaspativarkatha.MainActivity
import com.spiritual.brihaspativarkatha.R
import androidx.core.net.toUri

object NotificationHelper {

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        soundRes: Int,
        deepLink: String
    ) {

        val channelId = "aarti_channel"

        createChannel(context, channelId, soundRes)

        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("deep_link", deepLink)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            2001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_app_logo_1024)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(context)
            .notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun createChannel(
        context: Context,
        channelId: String,
        soundRes: Int
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val soundUri = "android.resource://${context.packageName}/$soundRes".toUri()

            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channel = NotificationChannel(
                channelId,
                "Daily Aarti",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.setSound(soundUri, audioAttributes)

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}