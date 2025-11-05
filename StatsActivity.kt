package com.example.healthcheckerapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthcheckerapp.adapter.HealthAdapter
import com.example.healthcheckerapp.databinding.ActivityStatsBinding
import com.example.healthcheckerapp.viewmodel.HealthViewModel

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private val viewModel: HealthViewModel by viewModels()
    private val adapter = HealthAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.allData.observe(this) { data ->
            adapter.submitList(data)
        }
    }
}
