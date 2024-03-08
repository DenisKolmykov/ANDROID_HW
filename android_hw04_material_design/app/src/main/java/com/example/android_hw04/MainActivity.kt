package com.example.android_hw04

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var personName= ""
    var personPhoneNum = ""
    var personSex = ""
    var personGetMessAuthoris = false
    var personGetMessNews = false
    var personPoints = 0
    val switch = findViewById<Switch>(R.id.switchId)
    val checkBoxOne = findViewById<CheckBox>(R.id.checkboxOne)
    val checkBoxTwo = findViewById<CheckBox>(R.id.checkboxTwo)
    val inputPersonName =  findViewById<EditText>(R.id.inputName)

    @SuppressLint("SetTextI18n", "MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.isEnabled = false


        // слушаем на изменения поле ввода ИМЕНИ
        inputPersonName.addTextChangedListener {
            personName = inputPersonName.text.toString()
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }
        //слушаем кнопку удаления текста в форме ввода Имени
        val delTexyButton = findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.textInputName)
        delTexyButton.setEndIconOnClickListener {
            inputPersonName.text.clear()
            personName = ""
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }

        // слушаем на изменения поле ввода телефона
        val inputPersonPhoneNum =  findViewById<EditText>(R.id.inputPhone)
        inputPersonPhoneNum.addTextChangedListener {
            personPhoneNum = inputPersonPhoneNum.text.toString()
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }

        // выбор по радио группе
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener{_,buttonId ->
            when (buttonId){
                R.id.radioM -> personSex = "M"
                R.id.radioF -> personSex = "F"
            }
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }

        // переключатель "получать уведомления"
        switch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                checkBoxOne.isClickable = true
                checkBoxTwo.isClickable = true
            } else {
                checkBoxOne.isChecked = false
                checkBoxOne.isClickable = false

                checkBoxTwo.isChecked = false
                checkBoxTwo.isClickable = false
            }
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }
        //checkBox "Об авторизации на устройстве"
        checkBoxOne.setOnCheckedChangeListener { _, isCheck ->
            personGetMessAuthoris = isCheck
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }
        // checkBox "О новинках и предложениях"
        checkBoxTwo.setOnCheckedChangeListener { _, isCheck ->
            personGetMessNews = isCheck
            checkInputData (
                personName,
                personPhoneNum,
                personSex,
                switch,
                personGetMessAuthoris,
                personGetMessNews,
                buttonSave)
        }

        // формрование прогресс бара (Баллы)
        val progressPoints = findViewById<ProgressBar>(R.id.progressBar)
        val progressStatus = findViewById<TextView>(R.id.points)
        personPoints = Random.nextInt(101)
        progressStatus.text = "$personPoints/100"
        progressPoints.progress = personPoints

        // сохранение введенных данных
        buttonSave.setOnClickListener{
            Toast.makeText(this,"СОХРАНЕНО",Toast.LENGTH_SHORT).show()
                //создаем(или перезаписываем) объект класса Person
        }
    }
}

// метод включения кнопки SAVE - проверка корректности введнных данных по всем полям
private fun checkInputData(personName : String,
                           personPhoneNum : String,
                           personSex :String,switch : Switch,
                           personGetMessAuthoris: Boolean,
                           personGetMessNews: Boolean,
                           buttonSave : Button
) {
    // если все норм - кропка SAVE активна
    buttonSave.isEnabled = (personName.length in 1..40
            && personPhoneNum.isNotEmpty()
            && personSex.isNotEmpty()
            && (!switch.isChecked
            || (switch.isChecked && (personGetMessAuthoris || personGetMessNews))))
}



