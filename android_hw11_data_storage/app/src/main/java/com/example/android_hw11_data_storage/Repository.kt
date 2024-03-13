package com.example.android_hw11_data_storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import android.widget.Toast


private const val PREFERENCE_NAME = "pref_name"
private const val SHARED_PREF_KEY = "shared_pref_name"
private const val FILE_NAME = "saved_data.txt"

class Repository {
    private lateinit var prefs : SharedPreferences
    private  var inputValue: String? = null


    fun getData(context: Context): String{
        return when{
            getDataFromLocalVar() != null -> getDataFromLocalVar()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
//            loadDataFromFile(context) != null -> loadDataFromFile(context)!!
            else -> "No saved data !"
        }
    }

    //будет записывать значения и в SharedPreference, и в локальную переменную.
    fun savedData(context: Context, data: String){
        inputValue = data
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        prefs.edit().putString(SHARED_PREF_KEY, data).apply()

//        //запись в файл
//        saveDataToFile(context, data)
    }

    //будет очищать значение и в SharedPreference, и в локальной переменной.
    fun clearData(){
        inputValue = null
        prefs.edit().clear().apply()

//        val file = File(FILE_NAME)
//        if (file.exists()) {
//            file.delete()
//        }
    }

    private fun getDataFromLocalVar(): String? {
        return inputValue
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(SHARED_PREF_KEY, null)
    }

    //чтение из файла
    private fun loadDataFromFile (context: Context): String?{
        var fin: FileInputStream? = null
        return try{
            fin = context.openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)

        } catch (e: IOException){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            null
        }finally {
            fin?.close()
        }
    }

    //запись в файл
    private fun saveDataToFile(context: Context, dataToSave: String) {
        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(dataToSave.toByteArray())
            Toast.makeText(context, "сохранение в файл $FILE_NAME успешно", Toast.LENGTH_SHORT)
                .show()
        } catch (e: IOException) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        } finally {
            fos?.close()
        }
    }
}