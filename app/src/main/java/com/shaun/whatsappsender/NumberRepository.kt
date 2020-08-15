package com.shaun.whatsappsender

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData

class NumberRepository(private val numberDao:NumberDao) {

    val allNumbers: LiveData<List<Number>> =numberDao.getNumber()
      suspend fun insert(number:Number){
        Log.d(TAG, "insert: $number")
        numberDao.insert(number)
    }
    suspend fun delete(number: String){
        numberDao.deleteByNumber(number)
    }

}