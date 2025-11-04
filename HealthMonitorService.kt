package com.example.healthcheckerapp.services

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.healthcheckerapp.MainActivity
import com.example.healthcheckerapp.R

class HealthMonitorService : Service() {

    private val CHANNEL_ID = "health_monitor_channel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForeground(1, createNotification("Monitoring health data..."))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Background task logic here (fake example)
        Thread {
            while (true) {
                Thread.sleep(60000) // simulate 1 min interval
                // TODO: Add real monitoring logic e.g. step count or heart rate
            }
        }.start()
        return START_STICKY
    }

    private fun createNotification(content: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Health Checker")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_health)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(CHANNEL_ID, "Health Monitor Service", NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
