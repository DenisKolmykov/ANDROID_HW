package com.example.android_hw11_data_storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_hw11_data_storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val repository = Repository()
    private lateinit var inputText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dataFromRepo = repository.getData(this)
        binding.textFromRepo.text = dataFromRepo

        binding.saveButton.setOnClickListener {
            inputText = binding.inputText.text.toString()
            repository.savedData(this, inputText)
            binding.textFromRepo.text = inputText
        }

        binding.eraseButton.setOnClickListener {
            repository.clearData()
            dataFromRepo = repository.getData(this)
            binding.textFromRepo.text = dataFromRepo
        }
    }
}