package com.example.android_hw15_room_sqlite_v3

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Word::class
    ],
    version = 1,
    exportSchema = true
)

abstract class DictDatabase: RoomDatabase(){
    abstract fun dictDao(): DictDao

}