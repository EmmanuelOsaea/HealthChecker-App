package com.example.healthguardian.ui
package com.example.healthchecker

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthguardian.data.HealthHistoryRepository
import com.example.healthguardian.data.HealthTipRepository
import com.example.healthguardian.databinding.ActivityMainBinding
import kotlin.random.Random
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tipRepo: HealthTipRepository
    private lateinit var historyRepo: HealthHistoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
 
        db = HealthDatabase.getDatabase(this)

        binding.btnCalculateBMI.setOnClickListener {
            val weight = binding.etWeight.text.toString().toDoubleOrNull() ?: 0.0
            val height = binding.etHeight.text.toString().toDoubleOrNull() ?: 1.0
            val bmi = weight / ((height / 100) * (height / 100))
            val category = when {
                bmi < 18.5 -> "Underweight"
                bmi in 18.5..24.9 -> "Normal"
                bmi in 25.0..29.9 -> "Overweight"
                else -> "Obese"
            }
            binding.tvResult.text = "BMI: %.2f (%s)".format(bmi, category)

            val data = HealthData(
                date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                bmi = bmi,
                steps = (3000..10000).random(),
                waterIntake = (1500..3000).random()
            )

            CoroutineScope(Dispatchers.IO).launch {
                db.healthDao().insertData(data)
            }
        }
    }
}
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
    binding.resultContainer.apply {
        alpha = 0f
        translationY = 50f  // start slightly below
        visibility = View.VISIBLE
        animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(600)
            .start()
    }
}
    }

    
private fun pulseButton() {
    binding.btnRunCheck.animate()
        .scaleX(1.1f)
        .scaleY(1.1f)
        .setDuration(300)
        .withEndAction {
            binding.btnRunCheck.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .start()
        }
        .start()
}


private fun animateTip(tipView: TextView) {
    tipView.apply {
        alpha = 0f
        translationX = -30f  // start slightly left
        visibility = View.VISIBLE
        animate()
            .alpha(1f)
            .translationX(0f)
            .setDuration(500)
            .setStartDelay(200)  // small delay for staggered effect
            .start()
    }
}

binding.tvResult.text = result
animateTip(binding.tvResult)  // assuming tip is in the same TextView

binding.viewStatsButton.setOnClickListener {
    val intent = Intent(this, HealthStatsActivity::class.java)
    startActivity(intent)
}
