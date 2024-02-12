package com.example.androidhw01

import android.graphics.Color
import android.view.View
import com.example.androidhw01.databinding.ActivityMainBinding

enum class SeatsStatus(val value: String) {
    ALL_SEATS_FREE("Все места свободны"),
    SEATS_LEFT("Осталось мест: "),
    BUST("Пассажиров слишком много!");

    fun setAllSeatsFreeStyle (binding: ActivityMainBinding){
        binding.textCenter.setTextColor(Color.parseColor("#20961e")) //смена цвета на зеленый
        //binding.buttonMinus.visibility = View.INVISIBLE
        binding.buttonMinus.isEnabled = false

        binding.buttonClear.visibility = View.INVISIBLE
        //binding.buttonPlus.visibility = View.VISIBLE
        binding.buttonPlus.isEnabled = true
    }

    fun setSeatsLeftStyle (binding: ActivityMainBinding){
        binding.textCenter.setTextColor(Color.parseColor("#3836b5")) //смена цвета на синий
        //binding.buttonMinus.visibility = View.VISIBLE
        binding.buttonMinus.isEnabled = true
        binding.buttonClear.visibility = View.INVISIBLE
        //binding.buttonPlus.visibility = View.VISIBLE
        binding.buttonPlus.isEnabled = true
    }
    fun setBustStyle (binding: ActivityMainBinding){
        binding.textCenter.setTextColor(Color.parseColor("#cf2d2d")) //смена цвета на красный
        binding.buttonClear.visibility = View.VISIBLE
        //binding.buttonPlus.visibility = View.INVISIBLE
        binding.buttonPlus.isEnabled = false
    }
}
