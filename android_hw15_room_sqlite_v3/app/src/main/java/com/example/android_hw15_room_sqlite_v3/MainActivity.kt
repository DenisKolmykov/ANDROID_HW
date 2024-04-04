package com.example.android_hw15_room_sqlite_v3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.android_hw15_room_sqlite_v3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val dictDao = (application as App).db.dictDao()
                return MainViewModel(dictDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var searchWord = ""
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textInput.addTextChangedListener {
            searchWord = binding.textInput.text.toString()
        }

        binding.buttonAdd.setOnClickListener { viewModel.onAddBtn(searchWord) }
        binding.buttonDelete.setOnClickListener { viewModel.onDeleteBtn() }


        lifecycleScope.launchWhenStarted {
            viewModel.allWords
                .collect() { words ->
                    binding.searchData
                        .text = words.joinToString(
                        separator = "\r\n"
                    )
                    binding.dictCounter
                        .text = words.size.toString()
                }
        }

    }
}