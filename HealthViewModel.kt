package com.example.healthchecker.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.healthchecker.data.HealthTipRepository
import android.app.Application
import com.example.healthchecker.data.HealthRecord
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class HealthViewModel(context: Context, application: Application) : AndroidViewModel(application) {
    private val repository = HealthTipRepository(context)

    fun getHealthTip(): String = repository.getRandomTip()
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
