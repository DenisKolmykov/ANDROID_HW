package com.example.androidhw02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customView = findViewById<CustomView>(R.id.custom_view)
        customView.setTopString("верхняя строчка, настроенная из кода")
        customView.setBottomString("нижняя строчка, настроенная из кода")

    }
}