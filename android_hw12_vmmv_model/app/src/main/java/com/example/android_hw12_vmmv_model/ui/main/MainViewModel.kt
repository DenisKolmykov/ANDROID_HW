package com.example.android_hw12_vmmv_model.ui.main

import android.util.Log
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

    private val _inputData = MutableStateFlow(InputData())
    val inputData = _inputData.asStateFlow()

    private val _notFound = Channel<String>()
//    val notFound = _notFound.receiveAsFlow()

    private val _error = Channel<String>()
//    val error = _error.receiveAsFlow()



    fun onStopSearchButtonInClick () {

//        viewModelScope.launch {
                _state.value = State.Success("Searching Stopped")

//        }
    }


    fun onSearchTextInput(){
        viewModelScope.launch{
            val inputSearchData = inputData.value.results
            val isSearchInputEmpty = inputSearchData.isBlank()
            if (isSearchInputEmpty || inputSearchData.length < 3) {
                val inputDataError = "Search data empty or less then 3 char"
                _state.value = State.Error(inputDataError)
            } else {
                _state.value = State.Searching
                try {
                    repository.getData()
                    _state.value = State.Success(inputSearchData)
                } catch (e: RuntimeException) {
                    _state.value = State.Success(e.message)

                }
            }
        }
    }
}