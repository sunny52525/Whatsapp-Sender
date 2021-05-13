package com.shaun.whatsappsender

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NumberViewModel
@Inject constructor(context: BaseApplication) : ViewModel() {

    private var repository: NumberRepository
    var allNumbers: LiveData<List<Number>>
    var fragNumber: MutableLiveData<Int> = MutableLiveData(0)

    init {
        Log.d(TAG, "Created: ")
        val numberDao = NumberRoomDatabase.getDataBase(context).NumberDao()
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