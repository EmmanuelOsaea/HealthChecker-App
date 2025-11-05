



package com.example.healthcheckerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "health_data")
data class HealthData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val heartRate: Int,
    val steps: Int,
    val hydrationLevel: Int,
    val date: String,
    val bmi: Double,
    val steps: Int,
    val waterIntake: Int // in milliliters
    val recordedAt: Date = Date()
     )
