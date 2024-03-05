package com.example.android_hw09_quiz_relize_en_calendar.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android_hw05_quiz.databinding.FragmentStartScreenBinding
import com.example.android_hw05_quiz.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

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

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        binding.quizNameStart.text = quizNames
        val bundle = Bundle().apply {
            putString(ARG_PARAM3, quizNames)
        }

        val startButton = binding.startButton
        val birthDateButton = binding.birthDateButton
        birthDateButton.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.date_dialog_title))
                .build()
            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
                val birthDate = "${resources.getString(R.string.date_snackbar_title)} ${dateFormat.format(calendar.time)}"
                Snackbar.make(birthDateButton,birthDate, Snackbar.LENGTH_SHORT).show()

                birthDateButton.visibility = View.GONE
                startButton.visibility = View.VISIBLE

            }
            dateDialog.show(requireFragmentManager(), "DatePicker")

        }

        startButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_StartScreen_to_QuizScreen, args = bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

