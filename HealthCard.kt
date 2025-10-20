package com.example.healthchecker.ui.components

data class HealthCard(
    val heartRate: Int,
    val bloodPressure: Int,
    val temperature: Int,
    val healthTip: String
)
