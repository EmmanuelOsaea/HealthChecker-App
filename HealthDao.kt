package com.example.healthchecker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HealthDao {
    @Insert
    suspend fun insertData(data: HealthData)

    @Query("SELECT * FROM health_data ORDER BY id DESC")
    suspend fun getAllData(): List<HealthData>
}
