package com.example.android_hw08_quiz_relize_animation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_hw05_quiz.R
import com.example.android_hw05_quiz.databinding.FragmentResultScreenBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


class ResultScreen : Fragment() {
    private var peronResults = 0
    private var totalPoints = 0
    private var quizNames: String? = null


    private var _binding: FragmentResultScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{
            peronResults = it.getInt(ARG_PARAM1)
            totalPoints = it.getInt(ARG_PARAM2)
            quizNames = it.getString(ARG_PARAM3)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResultScreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quizName.text = quizNames
        val textRes = "$peronResults / $totalPoints"
        binding.results.text = textRes

        binding.endButton.setOnClickListener {
            findNavController().navigate(R.id.action_ResultScreen_to_StartScreen)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    companion object {
//        @JvmStatic
//        fun newInstance (personResults: Int, totalPoints: Int, quizNames: String) =
//            ResultScreen().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_PARAM1, personResults)
//                    putInt(ARG_PARAM2, totalPoints)
//                    putString(ARG_PARAM3, quizNames)
//                }
//            }
//    }

}