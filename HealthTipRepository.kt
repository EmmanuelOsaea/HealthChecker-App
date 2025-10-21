package com.example.healthguardian.data

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class HealthTipRepository(private val context: Context) {

    fun getRandomTip(): String {
        return try {
            val inputStream = context.assets.open("health-tips.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val jsonText = bufferedReader.use { it.readText() }
            val jsonObject = JSONObject(jsonText)
            val tipsArray = jsonObject.getJSONArray("tips")
            val randomIndex = Random.nextInt(tipsArray.length())
            tipsArray.getString(randomIndex)
        } catch (e: Exception) {
            "Unable to load tip. Please try again later."
        }
    }
}
