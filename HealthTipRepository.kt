package com.example.healthchecker.data

import android.content.Context
import org.json.JSONObject

class HealthTipRepository(private val context: Context) {

    fun getRandomTip(): String {
        val jsonString = context.assets.open("health-tips.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val tipsArray = jsonObject.getJSONArray("tips")
        val randomIndex = (0 until tipsArray.length()).random()
        return tipsArray.getString(randomIndex)
    }
}
