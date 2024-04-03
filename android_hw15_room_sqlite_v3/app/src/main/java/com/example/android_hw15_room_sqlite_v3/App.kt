package com.example.android_hw15_room_sqlite_v3

import android.app.Application
import androidx.room.Room

class App: Application() {
    lateinit var db: DictDatabase

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        db = Room
            .inMemoryDatabaseBuilder(
            applicationContext,
                DictDatabase::class.java
            )
            .build()
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }
}