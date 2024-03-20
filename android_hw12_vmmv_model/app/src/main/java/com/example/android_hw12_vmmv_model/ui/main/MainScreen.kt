package com.example.android_hw12_vmmv_model.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.android_hw12_vmmv_model.databinding.MainScreenFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainScreen : Fragment() {

    companion object {
        fun newInstance() = MainScreen()
    }

    private lateinit var binding: MainScreenFragmentBinding
    private val viewModel: MainViewModel by viewModels {ViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchData = ""

        binding.searchData.addTextChangedListener {
            searchData = binding.searchData.text.toString()
            viewModel.onSearchTextInput(searchData)
        }

        binding.searchButton.setOnClickListener {
//            searchData = binding.searchData.text.toString()
            viewModel.onSearchButtonInClick(searchData)
        }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect(){state ->
                        when(state){
                            State.Start -> {
                                binding.progressBar.isVisible = false
                                binding.searchButton.isEnabled = false
                                binding.textInputLayout.error = null
                            }
                            State.Searching -> {
                                binding.progressBar.isVisible = true
                                binding.searchButton.isEnabled = false
                                binding.textInputLayout.error = null
                            }
                            is State.Success -> {
                                binding.progressBar.isVisible = false
                                binding.searchButton.isEnabled = true
                                binding.textInputLayout.error = null
                                binding.searchResults.text = state.results
                            }
                            is State.Error -> {
                                binding.progressBar.isVisible = false
                                binding.searchButton.isEnabled = false
                                binding.textInputLayout.error = state.errorMessage
//                                Snackbar.make(requireView(), state.errorMessage,Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
    }
}