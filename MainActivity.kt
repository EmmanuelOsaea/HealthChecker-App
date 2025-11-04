package com.example.healthguardian.ui
package com.example.healthcheckerapp

import android.os.Looper
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
import android.widget.TextView
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.media.MediaPlayer
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.content.Intent
import android.widget.Toast
import com.example.healthcheckerapp.services.HealthMonitorService


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tipRepo: HealthTipRepository
    private lateinit var historyRepo: HealthHistoryRepository
    private lateinit var mediaPlayer: MediaPlayer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)  
   
        binding.btnStartMonitoring.setOnClickListener {
            val intent = Intent(this, HealthMonitorService::class.java)
            startForegroundService(intent)
            Toast.makeText(this, "Monitoring Started", Toast.LENGTH_SHORT).show()
        }

        binding.btnStopMonitoring.setOnClickListener {
            stopService(Intent(this, HealthMonitorService::class.java))
            Toast.makeText(this, "Monitoring Stopped", Toast.LENGTH_SHORT).show()
        }
    }
}
        val checkButton: Button = findViewById(R.id.check_button)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)

        mediaPlayer = MediaPlayer.create(this, R.raw.heartbeat)

        checkButton.setOnClickListener {
            healthStatus.text = "Analyzing your health data..."
            progressBar.visibility = ProgressBar.VISIBLE
            checkButton.isEnabled = false

            // Start heartbeat sound
            mediaPlayer.isLooping = true
            mediaPlayer.start()

            // Pulse animation for progress bar
            val pulse = ScaleAnimation(
                1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            ).apply {
                duration = 600
                repeatMode = Animation.REVERSE
                repeatCount = Animation.INFINITE
            }
            progressBar.startAnimation(pulse)

            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.clearAnimation()
                progressBar.visibility = ProgressBar.GONE
                mediaPlayer.pause()
                checkButton.isEnabled = true

                val bmi = calculateBMI(70.0, 1.75)
                val hydration = checkHydration(8)
                val heartRate = simulateHeartRate()

                val result = """
                    ✅ Health Summary:
                    - BMI: $bmi (${bmiStatus(bmi)})
                    - Hydration: $hydration
                    - Heart Rate: $heartRate bpm
                """.trimIndent()

                
           
            val intent = Intent(this, HealthResultActivity::class.java)
intent.putExtra("BMI", bmi)
intent.putExtra("HYDRATION", hydration)
intent.putExtra("HEARTRATE", heartRate)
startActivity(intent)
            
            
            
            
            
            }, 2500)
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        return String.format("%.1f", weight / (height * height)).toDouble()
    }

    private fun bmiStatus(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun checkHydration(glasses: Int): String {
        return if (glasses >= 8) "Well hydrated 💧" else "Drink more water 🚰"
    }

    private fun simulateHeartRate(): Int {
        return Random.nextInt(60, 100)
    }





        
     val healthStatus: TextView = findViewById(R.id.health_status)
        healthStatus.text = "Welcome to Health Checker App 💪🏿"
    }
}
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


viewModel.records.observe(this) { records ->
    // Update chart when records change
    val entries = viewModel.getBmiEntries()
    val dataSet = LineDataSet(entries, "BMI Trend")
    dataSet.color = Color.BLUE
    dataSet.valueTextColor = Color.BLACK
    dataSet.lineWidth = 2f
    dataSet.circleRadius = 4f
    dataSet.setCircleColor(Color.RED)

    val lineData = LineData(dataSet)
    binding.bmiChart.data = lineData
    binding.bmiChart.invalidate()
}



   private fun simulateHealthCheck(): String {
        val options = listOf("Healthy", "Caution", "Alert")
        return options.random()
    }

val intent = Intent(this, HealthResultActivity::class.java)
intent.putExtra("score", score)
startActivity(intent)
overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    
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
