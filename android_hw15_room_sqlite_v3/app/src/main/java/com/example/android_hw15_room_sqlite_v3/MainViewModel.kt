package com.example.android_hw15_room_sqlite_v3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.android_hw15_room_sqlite_v3.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainViewModel (private val dictDao: DictDao): ViewModel() {

    val allWords = this.dictDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    fun onAddBtn(searchWord: String){
        viewModelScope.launch {
            val index = allWords.value.indexOfFirst { it.word == searchWord }
            if(index >= 0){
                val word = allWords.value[index].copy(
                    counter = allWords.value[index].counter + 1
                )
                dictDao.update(word)
            } else {
                dictDao.insert(
                    NewWord(
                        word = searchWord,
                        counter = 1
                    )
                )
            }
        }
    }

    fun onDeleteBtn() {
        viewModelScope.launch {
            allWords.value.forEach {
                 dictDao.delete(it)
            }
        }
    }
}