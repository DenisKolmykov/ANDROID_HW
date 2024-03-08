package com.example.android_hw10_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.android_hw10_timer.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.slider.Slider
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TIMER_VALUE_KEY = "timerValue"
private const val CURRENT_TIME_REMAIN_KEY = "time_test"
private const val START_BUTTON_ACTIVATED = "isActivated"

class MainActivity : AppCompatActivity() {
    private var timeCount = 0
//    private var t = 0

    private var timerValue = 0
    private var startButtonActivated = false //флаг что кнопка нажата (его передаем при перевороте экрана)

    private lateinit var startButton : Button
    private lateinit var slider: Slider
    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var timerCountText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        slider = binding.slider
        startButton = binding.startButton
        progressBar = binding.progressBar
        timerCountText = binding.timerCountText

        if (savedInstanceState != null) {
            timeCount = savedInstanceState.getInt(CURRENT_TIME_REMAIN_KEY) //текущее значение тайменра
            timerValue = savedInstanceState.getInt(TIMER_VALUE_KEY) //установленное значение таймера
            startButtonActivated = savedInstanceState.getBoolean(START_BUTTON_ACTIVATED)// флаг что кнопка нажата
            if (startButtonActivated && timeCount !=0) {//если кнопка нажата
                startTimer(timerValue) // вызываем метод перерисовки тайменра
            }
        }

        slider.addOnChangeListener(){
                _, sliderValue, _ ->
            timerValue = sliderValue.toInt()
            timerCountText.text = sliderValue.toInt().toString()
            progressBar.max = timerValue // чтоб заполнение соответствовало выбранному значению таймера
        }

        startButton.setOnClickListener() {
            startTimer(timerValue)
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun startTimer(timerValue: Int) {
        // раз мы в этом методе (и он еще не закончен) - то когдато была нажата startButton
        startButtonActivated = true

        //блокируем startButton и slider
        startButton.isEnabled = false
        slider.isEnabled = false

        // Создаем корутину, которая вызывает обновление progressBar каждую секунду
        GlobalScope.launch(Dispatchers.Main) {
            for(t in timeCount..timerValue) {
                // обновляем progressBar // Ждем одну секунду
                progressBar.progress = t
                timerCountText.text = (timerValue - t).toString()
                delay(1000)
            }
            // по окончании таймера - открываем startButton и slider
            startButton.isEnabled = true
            slider.isEnabled = true

            progressBar.progress = 0 // сбрасываем progressBar
            timeCount = 0 //обнуляем значение таймера

            timerCountText.text = timerValue.toString() // устанавливаем текст таймера на значение установленное на слайдере
            startButtonActivated = false // флаг ативности кнопки отключаем
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(TIMER_VALUE_KEY, timerValue) //сохраняем текущее значени етаймера
        outState.putInt(CURRENT_TIME_REMAIN_KEY, progressBar.progress)
        outState.putBoolean(START_BUTTON_ACTIVATED, startButtonActivated)
        super.onSaveInstanceState(outState)
    }
}