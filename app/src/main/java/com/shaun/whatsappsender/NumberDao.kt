package com.shaun.whatsappsender

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberDao{
    @Query("SELECT * from number_table")
    fun getNumber():LiveData<List<Number>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(number: Number)

    @Query("DELETE FROM number_table")
    suspend fun deleteAll()

    @Query("DELETE FROM number_table WHERE Number= :number")
        suspend fun deleteByNumber(number: String)


}