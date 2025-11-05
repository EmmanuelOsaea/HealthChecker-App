package com.example.healthchecker.ui
package com.example.healthcheckerapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.healthchecker.data.HealthTipRepository
import android.app.Application
import com.example.healthchecker.data.HealthRecord
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.healthcheckerapp.data.HealthDatabase
import com.example.healthcheckerapp.repository.HealthRepository


class HealthViewModel(context: Context, application: Application) : AndroidViewModel(application) {
    private val repository = HealthTipRepository(context)
    val allData: LiveData<List<HealthData>>
    fun getHealthTip(): String = repository.getRandomTip()
}

init {
        val dao = HealthDatabase.getDatabase(application).healthDao()
        repository = HealthRepository(dao)
        allData = repository.allData
    }




private val _records = MutableLiveData<List<HealthRecord>>()
    val records: LiveData<List<HealthRecord>> = _records

    fun loadRecords() {
        viewModelScope.launch {
            _records.value = dao.getAllRecords()
        }
    }

    fun addRecord(record: HealthRecord) {
        viewModelScope.launch {
            dao.insertRecord(record)
            loadRecords()
        }
    }
}

fun getBmiEntries(): List<Entry> {
    return _records.value?.mapIndexed { index, record ->
        Entry(index.toFloat(), record.bmi)
    } ?: emptyList()
}

fun insert(data: HealthData) = viewModelScope.launch {
        repository.insert(data)
    }

    fun clearAll() = viewModelScope.launch {
        repository.clearAll()
    }
}



