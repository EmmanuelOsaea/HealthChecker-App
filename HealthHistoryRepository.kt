package com.example.healthguardian.data

import android.content.Context
import android.content.SharedPreferences

class HealthHistoryRepository(context: Context) {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("health_history", Context.MODE_PRIVATE)

    fun saveResult(status: String, message: String) {
        val oldHistory = sharedPrefs.getString("history", "") ?: ""
        val newEntry = "🩺 Status: $status\n$message\nTime: ${java.util.Date()}\n"
        val updatedHistory = "$newEntry\n-------------------------\n$oldHistory"
        sharedPrefs.edit().putString("history", updatedHistory).apply()
    }

    fun getHistory(): List<String> {
        val historyString = sharedPrefs.getString("history", "") ?: ""
        return if (historyString.isEmpty()) emptyList() else historyString.split("-------------------------")
    }

    fun clearHistory() {
        sharedPrefs.edit().remove("history").apply()
    }
}
