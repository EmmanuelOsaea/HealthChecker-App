
package com.example.healthchecker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.healthchecker.data.HealthHistoryRepository
import com.example.healthchecker.databinding.ActivityHistoryBinding
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = HealthHistoryRepository(this)
        val history = repo.getHistory()
        val displayText = StringBuilder()

        for (i in 0 until history.length()) {
            val item = history.getJSONObject(i)
            val time = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
                .format(Date(item.getLong("timestamp")))

            displayText.append("🕒 $time\n")
            displayText.append("• Status: ${item.getString("status")}\n")
            displayText.append("• Message: ${item.getString("message")}\n\n")
        }

        binding.tvHistory.text = displayText.toString()
    }
}

binding.btnClearHistory.setOnClickListener {
    val repo = HealthHistoryRepository(this)
    repo.clearHistory()
    binding.tvHistory.text = "History cleared successfully."
}
