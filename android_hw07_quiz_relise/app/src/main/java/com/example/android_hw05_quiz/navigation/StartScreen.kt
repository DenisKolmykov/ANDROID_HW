package com.example.android_hw05_quiz.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_hw05_quiz.R
import com.example.android_hw05_quiz.databinding.FragmentStartScreenBinding

private const val quizNames = "IT и ВСЕ ЧТО РЯДОМ"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartScreen : Fragment() {

    private var _binding: FragmentStartScreenBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quizNameStart.text = quizNames
        val bundle = Bundle().apply {
            putString(ARG_PARAM3, quizNames)
        }

        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartScreen_to_QuizScreen, args = bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}