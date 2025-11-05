package com.example.healthcheckerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcheckerapp.data.HealthData
import com.example.healthcheckerapp.databinding.ItemHealthBinding

class HealthAdapter :
    ListAdapter<HealthData, HealthAdapter.HealthViewHolder>(DiffCallback()) {

    class HealthViewHolder(private val binding: ItemHealthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HealthData) {
            binding.tvHeartRate.text = "❤️ Heart Rate: ${data.heartRate} bpm"
            binding.tvSteps.text = "👣 Steps: ${data.steps}"
            binding.tvHydration.text = "💧 Hydration: ${data.hydrationLevel}%"
            binding.tvDate.text = data.recordedAt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthViewHolder {
        val binding = ItemHealthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<HealthData>() {
        override fun areItemsTheSame(oldItem: HealthData, newItem: HealthData) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: HealthData, newItem: HealthData) = oldItem == newItem
    }
}
