package com.example.healthchecker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HealthResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_result)

        val resultText: TextView = findViewById(R.id.resultText)
        val tipsText: TextView = findViewById(R.id.tipsText)

        // Get data from MainActivity
        val bmi = intent.getDoubleExtra("BMI", 0.0)
        val hydration = intent.getStringExtra("HYDRATION")
        val heartRate = intent.getIntExtra("HEARTRATE", 0)

        // Display the result summary
        resultText.text = """
            ✅ Health Summary:
            - BMI: $bmi (${bmiStatus(bmi)})
            - Hydration: $hydration
            - Heart Rate: $heartRate bpm
        """.trimIndent()

        // Generate and show personalized tip
        tipsText.text = getHealthTip(bmi, hydration, heartRate)
  val recheckButton: Button = findViewById(R.id.btnRecheck)
recheckButton.setOnClickListener {
    finish() // Closes this activity and returns to MainActivity
}
    
    
    
    }

    private fun bmiStatus(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun getHealthTip(bmi: Double, hydration: String?, heartRate: Int): String {
        return when {
            bmi < 18.5 -> "Eat more protein and healthy fats to gain strength."
            bmi in 25.0..29.9 -> "Go for daily walks or light workouts to stay fit."
            bmi >= 30 -> "Consider a balanced diet plan and stay active daily."
            hydration?.contains("more water") == true -> "Drink at least 8 glasses of water a day."
            heartRate > 90 -> "Your heart rate is a bit high — take deep breaths and relax."
            else -> "Great! Keep up your healthy lifestyle."
        }
    }
}
