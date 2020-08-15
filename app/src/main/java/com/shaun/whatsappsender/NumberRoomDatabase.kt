package com.shaun.whatsappsender

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Number::class],version = 1)
abstract class NumberRoomDatabase:RoomDatabase(){
    abstract fun NumberDao():NumberDao

    companion object{
        @Volatile
        private var INSTANCE:NumberRoomDatabase?=null
        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ):NumberRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    NumberRoomDatabase::class.java,
                    "number_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(NumberDataBaseCallBack(scope))
                    .build()
                INSTANCE=instance
                instance
            }
        }
        private class NumberDataBaseCallBack(private val scope: CoroutineScope):RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let {database ->
                    scope.launch(Dispatchers.IO){
                        populateDatabase(database.NumberDao())
                    }
                }
            }

             suspend fun populateDatabase(numberDao: NumberDao) {

                      var Number=Number("+917004468293")

                 numberDao.insert(Number)

            }
        }
    }
}