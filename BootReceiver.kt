package com.example.healthcheckerapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.healthcheckerapp.services.HealthMonitorService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, HealthMonitorService::class.java)
            context.startForegroundService(serviceIntent)
        }
    }
}
