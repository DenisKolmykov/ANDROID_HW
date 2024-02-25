package com.example.android_hw05_quiz.questions

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.android_hw05_quiz.R
import com.example.android_hw05_quiz.databinding.ActivityQuestionOneBinding

class QuestionOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityQuestionOneBinding.inflate(layoutInflater)
        val buttonAnswer = binding.answerButton
        val firstQuestionText = "Устье реки - это:"
        binding.textQuestion.text = firstQuestionText

        val answer1 = "ответ 1"
        val answer2 = "ответ 2"
        val answer3 = "ответ 3"
        val answer4 = "ответ 4"

        val correctAnswer = 3
        var personAnswer = 0

        binding.radio1.text = answer1
        binding.radio2.text = answer2
        binding.radio3.text = answer3
        binding.radio4.text = answer4


        val radioGroup = binding.radioGroup
        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            when (buttonId) {
                R.id.radio1 -> personAnswer = 1
                R.id.radio2 -> personAnswer = 2
                R.id.radio3 -> personAnswer = 3
                R.id.radio4 -> personAnswer = 4
            }
        }
        buttonAnswer.setOnClickListener {
            binding.checkAnswer.isVisible = true
            val mediaPlayer = if (personAnswer == correctAnswer) {
                binding.checkAnswer.setTextColor(resources.getColor(R.color.green, null))
                binding.checkAnswer.text = "ПРАВИЛЬНО!!!"
                MediaPlayer.create(applicationContext,R.raw.right_answer)

            } else {
                binding.checkAnswer.setTextColor(resources.getColor(R.color.red, null))
                binding.checkAnswer.text = "НЕ ВЕРНО!!!"

                MediaPlayer.create(applicationContext,R.raw.wrong_answer)
            }
            mediaPlayer.start()
        }

//        buttonNext.setOnClickListener {
//            val intent = Intent(this, QuestionTwo::class.java)
//            startActivity(intent)
//        }
        setContentView(binding.root)
    }
}