package com.shaun.whatsappsender

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NumberViewModel (application: Application):AndroidViewModel(application){
    var repository:NumberRepository
    var allNumbers: LiveData<List<Number>>


    init {
        val numberDao = NumberRoomDatabase.getDataBase(application, viewModelScope).NumberDao()
        repository = NumberRepository(numberDao)
        allNumbers = repository.allNumbers

    }

    fun delete(number: String) {
        GlobalScope.launch {
            repository.delete(number)
        }
    }

    fun insert(number: Number) {
        Log.d(TAG, "insert: $number")
        GlobalScope.launch {
            repository.insert(number)
        }
    }
}