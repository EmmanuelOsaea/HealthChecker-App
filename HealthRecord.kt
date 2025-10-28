package com.example.healthchecker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_records")
data class HealthRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val bmi: Float,
    val weight: Float,
    val height: Float
)
