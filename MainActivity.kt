package com.example.healthchecker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.healthchecker.databinding.ActivityMainBinding
import com.example.healthchecker.ui.HealthViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HealthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = HealthViewModel(this)

        binding.btnRunCheck.setOnClickListener {
            runHealthCheck()
        }
    }

    private fun runHealthCheck() {
       binding.loadingSpinner.visibility = View.VISIBLE  // show spinner
       binding.btnRunCheck.isEnabled = false             // disable button
       CoroutineScope(Dispatchers.Main).launch {
       binding.tvResult.text = "Analyzing your vitals..."
        binding.resultContainer.setBackgroundColor(Color.LTGRAY)

        // Pulse while loading
        for (i in 1..3) {
            pulseButton()
            delay(600)
        }

        delay(1500) // simulate analysis time


      // Random outcome simulation
        val outcome = listOf("Healthy", "Caution", "Alert").random()

        when (outcome) {
            "Healthy" -> {
        
        binding.tvResult.text = "You're in good health! ✅
                Keep it up!"
        binding.resultContainer.setBackgroundColor(Color.parseColor("4CAF50"))

        "Caution" -> {
                binding.tvResult.text = "⚠️ Minor issues detected. Take a short rest."
                binding.resultContainer.setBackgroundColor(Color.parseColor("#FFC107")) // yellow
            }
            "Alert" -> {
                binding.tvResult.text = "❌ High stress or irregular vitals detected!"
                binding.resultContainer.setBackgroundColor(Color.parseColor("#F44336")) // red
            }
        }

         
        fadeInResult()

        binding.loadingSpinner.visibility = View.GONE  // hide spinner
        binding.btnRunCheck.isEnabled = true           // re-enable button
    }
}
        
           binding.progressBar.visibility = View.VISIBLE
        
           binding.tvResult.text = "Analyzing health data..."
        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)
            binding.progressBar.visibility = View.GONE

            val heartRate = (60..120).random()
            val bp = (110..150).random()
            val temp = (36..39).random()
            val tip = viewModel.getHealthTip()

            val result = """
                ❤️ Heart Rate: $heartRate bpm
                💉 Blood Pressure: $bp mmHg
                🌡️ Temperature: $temp°C
                
                💡 Tip: $tip
            """.trimIndent()

            binding.tvResult.text = result
        fadeInResult()
        }
    }
}

private fun fadeInResult() {
    binding.resultContainer.alpha = 0f
    binding.resultContainer.animate()
        .alpha(1f)
        .setDuration(600)
        .start()
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

// Simulate loading or data check
binding.tvResult.text = "Analyzing your vitals..."
binding.resultContainer.setBackgroundColor(Color.LTGRAY)

// Start pulsing the button while checking
for (i in 1..3) {
    pulseButton()
    delay(600)
}





// Save the last result locally
val sharedPref = getSharedPreferences("HealthPrefs", MODE_PRIVATE)
with(sharedPref.edit()) {
    putString("lastResultText", binding.tvResult.text.toString())
    putInt("lastResultColor", (binding.resultContainer.background as? ColorDrawable)?.color ?: Color.WHITE)
    apply()
}
