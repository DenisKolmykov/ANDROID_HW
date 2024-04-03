package com.example.android_hw15_room_sqlite_v3

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NewWord(
    @PrimaryKey
    @ColumnInfo (name = "id")
    val id: Int? = null,

    @ColumnInfo (name = "word")
    val word: String,

    @ColumnInfo (name = "counter")
    val counter: Int
)
