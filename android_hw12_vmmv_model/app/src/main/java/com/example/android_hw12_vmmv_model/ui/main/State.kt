package com.example.android_hw12_vmmv_model.ui.main

sealed class State {
    object Start : State()
    object Searching : State()
    data class Success(val results: String?) : State()
    data class Error (val errorMessage: String) :State()
}