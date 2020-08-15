package com.shaun.whatsappsender

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number_table")
data class Number(@PrimaryKey @ColumnInfo(name = "Number") val number: String){}
