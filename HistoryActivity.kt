package com.example.healthguardian.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthguardian.data.HealthHistoryRepository
import com.example.healthguardian.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyRepo: HealthHistoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyRepo = HealthHistoryRepository(this)

        val historyList = historyRepo.getHistory()
        binding.tvHistory.text = historyList.joinToString("\n\n")

        binding.btnClearHistory.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Clear History")
                .setMessage("Are you sure you want to delete all health history?")
                .setPositiveButton("Yes") { _, _ ->
                    historyRepo.clearHistory()
                    binding.tvHistory.text = ""
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
