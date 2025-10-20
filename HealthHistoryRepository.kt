package com.example.healthchecker.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class HealthHistoryRepository(private val context: Context) {

    private val prefs = context.getSharedPreferences("HealthHistory", Context.MODE_PRIVATE)

    fun saveResult(status: String, message: String) {
        val existing = prefs.getString("history", "[]")
        val jsonArray = JSONArray(existing)

        val newEntry = JSONObject()
        newEntry.put("status", status)
        newEntry.put("message", message)
        newEntry.put("timestamp", System.currentTimeMillis())

        jsonArray.put(newEntry)

        prefs.edit().putString("history", jsonArray.toString()).apply()
    }

    fun getHistory(): JSONArray {
        val existing = prefs.getString("history", "[]")
        return JSONArray(existing)
    }
}
