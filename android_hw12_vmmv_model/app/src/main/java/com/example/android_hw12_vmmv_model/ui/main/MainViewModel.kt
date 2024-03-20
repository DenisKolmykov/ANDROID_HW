package com.example.android_hw12_vmmv_model.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel (
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow<State> (State.Start)
    val state = _state.asStateFlow()

    private val _notFound = Channel<String>()
//    val notFound = _notFound.receiveAsFlow()

    private val _error = Channel<String>()
//    val error = _error.receiveAsFlow()


    fun onSearchButtonInClick (searchData: String) {
        viewModelScope.launch {
                _state.value = State.Searching
                try {
                    repository.getData()
                    _state.value = State.Success (searchData)
                } catch (e: RuntimeException){
                    _state.value = State.Success(e.message)
            }
        }
    }

    fun onSearchTextInput(searchData: String){
        viewModelScope.launch{
            val isSearchInputEmpty = searchData.isBlank()
            if (isSearchInputEmpty || searchData.length < 3) {
                val inputDataError = "Search data empty or less then 3 char"
                _state.value = State.Error(inputDataError)
                _error.send("Search data not entered!")
            } else {
                _state.value = State.Success(null)

            }
        }
    }
}