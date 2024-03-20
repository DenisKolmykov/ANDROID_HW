package com.example.android_hw12_vmmv_model.ui.main

import kotlinx.coroutines.delay

class Repository {
    private var count = 0

    suspend fun getData(): String {
        delay(3_000)
        return if (++count %2 == 0) {
            throw RuntimeException ("Searching data NOT FOUND!")
        } else "done"
    }
}