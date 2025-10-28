package com.example.healthchecker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.example.healthchecker.databinding.ActivityHealthStatsBinding

class HealthStatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealthStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chart: LineChart = binding.lineChart
        val entries = listOf(
            Entry(1f, 22.5f),
            Entry(2f, 23.1f),
            Entry(3f, 22.8f),
            Entry(4f, 23.0f),
            Entry(5f, 22.9f)
        )

        val dataSet = LineDataSet(entries, "BMI Progress")
        dataSet.color = getColor(R.color.purple_700)
        dataSet.setCircleColor(getColor(R.color.purple_700))
        dataSet.valueTextSize = 12f
        dataSet.lineWidth = 2f

        val lineData = LineData(dataSet)
        chart.data = lineData

        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisRight.isEnabled = false
        chart.description.text = "Weekly BMI Tracking"
        chart.invalidate()
    }
}
