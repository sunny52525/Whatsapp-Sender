package com.shaun.whatsappsender

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Number::class],version = 1)
abstract class NumberRoomDatabase:RoomDatabase(){
    abstract fun NumberDao():NumberDao

    companion object{
        @Volatile
        private var INSTANCE:NumberRoomDatabase?=null
        fun getDataBase(
            context: Context
        ):NumberRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    NumberRoomDatabase::class.java,
                    "number_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }

    }
}