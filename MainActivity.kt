package com.example.healthguardian.ui

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthguardian.data.HealthHistoryRepository
import com.example.healthguardian.data.HealthTipRepository
import com.example.healthguardian.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tipRepo: HealthTipRepository
    private lateinit var historyRepo: HealthHistoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tipRepo = HealthTipRepository(this)
        historyRepo = HealthHistoryRepository(this)

        binding.btnCheckHealth.setOnClickListener {
            pulseButton()
            val outcome = simulateHealthCheck()
            displayResult(outcome)
            fadeInResult()
        }

        binding.btnViewHistory.setOnClickListener {
            // Move to history screen
            startActivity(android.content.Intent(this, HistoryActivity::class.java))
        }

        binding.btnViewTips.setOnClickListener {
            val randomTip = tipRepo.getRandomTip()
            binding.tvTips.text = "💡 $randomTip"
        }
    }

    private fun simulateHealthCheck(): String {
        val options = listOf("Healthy", "Caution", "Alert")
        return options.random()
    }

    private fun displayResult(outcome: String) {
        when (outcome) {
            "Healthy" -> {
                binding.tvResult.text = "You're in good health! ✅ Keep it up!"
                historyRepo.saveResult(outcome, binding.tvResult.text.toString())
                binding.resultContainer.setBackgroundColor(Color.parseColor("#4CAF50"))
            }
            "Caution" -> {
                binding.tvResult.text = "⚠️ Minor issues detected. Take a short rest."
                historyRepo.saveResult(outcome, binding.tvResult.text.toString())
                binding.resultContainer.setBackgroundColor(Color.parseColor("#FFC107"))
            }
            "Alert" -> {
                binding.tvResult.text = "❌ High stress or irregular vitals detected!"
                historyRepo.saveResult(outcome, binding.tvResult.text.toString())
                binding.resultContainer.setBackgroundColor(Color.parseColor("#F44336"))
            }
        }
    }

    private fun fadeInResult() {
        val fadeIn = ObjectAnimator.ofFloat(binding.tvResult, "alpha", 0f, 1f)
        fadeIn.duration = 800
        fadeIn.start()
    }

    private fun pulseButton() {
        val pulse = ObjectAnimator.ofFloat(binding.btnCheckHealth, "scaleX", 1f, 1.1f, 1f)
        pulse.duration = 400
        pulse.start()
    }
}
