package com.example.healthchecker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_data")
data class HealthData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val bmi: Double,
    val steps: Int,
    val waterIntake: Int // in milliliters
)
