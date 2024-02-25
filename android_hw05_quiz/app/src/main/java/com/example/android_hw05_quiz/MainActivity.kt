package com.example.android_hw05_quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_hw05_quiz.databinding.ActivityMainBinding
import com.example.android_hw05_quiz.questions.QuestionOne

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val buttonStart = binding.startButton

        buttonStart.setOnClickListener {
            val intent = Intent(this, QuestionOne::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)

    }
}