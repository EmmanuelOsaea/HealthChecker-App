package com.example.healthchecker.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.healthchecker.data.HealthTipRepository

class HealthViewModel(context: Context) : ViewModel() {
    private val repository = HealthTipRepository(context)

    fun getHealthTip(): String = repository.getRandomTip()
}
