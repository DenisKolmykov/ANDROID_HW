package com.example.android_hw12_vmmv_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_hw12_vmmv_model.ui.main.MainScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainScreen.newInstance())
                .commitNow()
        }
    }
}