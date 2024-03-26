package com.example.android_hw12_vmmv_model.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_hw12_vmmv_model.databinding.MainScreenFragmentBinding
import kotlinx.coroutines.*
class MainScreen : Fragment() {

    companion object {
        fun newInstance() = MainScreen()
    }


    private lateinit var dataBinding: MainScreenFragmentBinding
    private val viewModel: MainViewModel by viewModels {ViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dataBinding = MainScreenFragmentBinding.inflate(inflater, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchData = ""

        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner


        dataBinding.searchData.addTextChangedListener {
            searchData = dataBinding.searchData.text.toString()
            viewModel.onSearchTextInput()
        }


//        dataBinding.searchButton.setOnClickListener {
//            viewModel.onStopSearchButtonInClick()
//        }
//        viewLifecycleOwner.lifecycleScope
//            .launchWhenStarted {
//                viewModel.state
//                    .collect(){state ->
//                        when(state){
//                            State.Start -> {
//                                dataBinding.progressBar.isVisible = false
//                                dataBinding.searchButton.isEnabled = false
//                                dataBinding.textInputLayout.error = null
//                            }
//                            State.Searching -> {
//                                dataBinding.progressBar.isVisible = true
//                                dataBinding.searchButton.isEnabled = false
//                                dataBinding.textInputLayout.error = null
//                            }
//                            is State.Success -> {
//                                dataBinding.progressBar.isVisible = false
//                                dataBinding.searchButton.isEnabled = true
//                                dataBinding.textInputLayout.error = null
//                                dataBinding.searchResults.text = state.results
//                            }
//                            is State.Error -> {
//                                dataBinding.progressBar.isVisible = false
//                                dataBinding.searchButton.isEnabled = false
//                                dataBinding.textInputLayout.error = state.errorMessage
////                                Snackbar.make(requireView(), state.errorMessage,Snackbar.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
//            }
    }
}