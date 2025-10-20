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
        }
    }
}
