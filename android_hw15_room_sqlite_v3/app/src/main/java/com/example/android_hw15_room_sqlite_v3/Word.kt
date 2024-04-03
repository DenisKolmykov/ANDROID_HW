package com.example.android_hw15_room_sqlite_v3

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dict")
data class Word(
    @PrimaryKey
    @ColumnInfo (name = "id")
    val id: Int,

    @ColumnInfo (name = "word")
    val word: String,

    @ColumnInfo (name = "counter")
    var counter: Int
)
