package com.example.android_hw09_quiz_relize_en_calendar.questions

import kotlin.collections.ArrayList

object QuizStorage {

    private const val questionsQuantity = 3
    private var questions: ArrayList<Question> = ArrayList(questionsQuantity)

    fun setQuestions(question1: Question,
                     question2: Question,
                     question3: Question
    ){
        questions.add(question1)
        questions.add(question2)
        questions.add(question3)
    }


    fun answer(results: ArrayList<Int>): Int {
        var res = 0
        for (i in 0 until questionsQuantity){
            if (results[i] == questions[i].correctAnswer){
                res += questions[i].points
            }
        }
        return res
    }
}