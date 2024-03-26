package com.example.android_hw12_vmmv_model.ui.main

sealed class State (
    open val isSearching: Boolean = false,
    open val errorMessage : String? = null,
    open val results: String? = null,
) {
    data object Start: State()

    data object Searching : State(isSearching = true)
    data class Success(
        override val results: String?) : State(results = results)
    data class Error (
        override val errorMessage: String?
    ) :State(errorMessage = errorMessage)

}