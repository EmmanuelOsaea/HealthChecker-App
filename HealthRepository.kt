package com.example.healthcheckerapp.repository

import com.example.healthcheckerapp.data.HealthDao
import com.example.healthcheckerapp.data.HealthData

class HealthRepository(private val dao: HealthDao) {
    val allData = dao.getAllData()

    suspend fun insert(data: HealthData) = dao.insertData(data)

    suspend fun clearAll() = dao.clearAll()
}
