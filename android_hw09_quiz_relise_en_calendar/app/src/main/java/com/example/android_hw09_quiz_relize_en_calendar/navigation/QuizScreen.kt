package com.example.android_hw09_quiz_relize_en_calendar.navigation

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController
import com.example.android_hw05_quiz.R
import com.example.android_hw05_quiz.databinding.FragmentQuizScreenBinding
import com.example.android_hw09_quiz_relize_en_calendar.questions.Question
import com.example.android_hw09_quiz_relize_en_calendar.questions.QuizStorage

val question1 = Question(
    "Укажите язык программирования:",
    "Yava",
    "Hotlin",
    "Ada",
    "Platon",
    3,
    10
)

val question2 = Question(
    "Что такое класс в ООП:",
    "помещение в организации объединения преподавателей",
    "помещение для учебных занятий",
    "объект определенного типа",
    "модель для создания объектов",
    4,
    20
)

val question3 = Question(
    "Что такое http:",
    "редактор HTML документов",
    "протокол позволяющий получать HTML документы",
    "luxery бренд верхней одежды",
    "luxery бренд верхней обуви",
    2,
    10
)

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuizScreen : Fragment() {

    private var quizNames: String? = null

    private var _binding: FragmentQuizScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{
            quizNames = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuizScreenBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textQuizTitle.text = quizNames
        // изменение цвета заголовка Quiz
        ObjectAnimator.ofArgb(binding.textQuizTitle,
            "textColor",
            Color.parseColor("#F44336"),
            Color.parseColor("#027180")
        ).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val personAnswers: ArrayList<Int> = ArrayList()

        binding.textQuestion1.text = question1.question
        val textPoints1 = "(${question1.points} баллов)"
        binding.textPoints1.text = textPoints1
        binding.radio11.text = question1.answer1
        binding.radio12.text = question1.answer2
        binding.radio13.text = question1.answer3
        binding.radio14.text = question1.answer4
        val radioGroup1 = binding.radioGroup1

        radioGroup1.setOnCheckedChangeListener { group, buttonId ->
            when (buttonId) {
                R.id.radio11 -> personAnswers.add(1)
                R.id.radio12 -> personAnswers.add(2)
                R.id.radio13 -> personAnswers.add(3)
                R.id.radio14 -> personAnswers.add(4)
            }
            getAnimatioRadioButtonCheck(group,buttonId )
        }


        binding.textQuestion2.text = question2.question
        val textPoints2 = "(${question2.points} баллов)"
        binding.textPoints2.text = textPoints2
        binding.radio21.text = question2.answer1
        binding.radio22.text = question2.answer2
        binding.radio23.text = question2.answer3
        binding.radio24.text = question2.answer4
        val radioGroup2 = binding.radioGroup2
        radioGroup2.setOnCheckedChangeListener { group, buttonId ->
            when (buttonId) {
                R.id.radio21 -> personAnswers.add(1)
                R.id.radio22 -> personAnswers.add(2)
                R.id.radio23 -> personAnswers.add(3)
                R.id.radio24 -> personAnswers.add(4)
            }
            getAnimatioRadioButtonCheck(group,buttonId )
        }

        binding.textQuestion3.text = question3.question
        val textPoints3 = "(${question3.points.toString()} баллов)"
        binding.textPoints3.text = textPoints3
        binding.radio31.text = question3.answer1
        binding.radio32.text = question3.answer2
        binding.radio33.text = question3.answer3
        binding.radio34.text = question3.answer4
        val radioGroup3 = binding.radioGroup3

        val sendingButton = binding.sendButton

        radioGroup3.setOnCheckedChangeListener { group, buttonId ->
            when (buttonId) {
                R.id.radio31 -> personAnswers.add(1)
                R.id.radio32 -> personAnswers.add(2)
                R.id.radio33 -> personAnswers.add(3)
                R.id.radio34 -> personAnswers.add(4)
            }
            getAnimatioRadioButtonCheck(group,buttonId )

            sendingButton.animate().apply {
                duration = 2000
                alpha(1f)
            }.start()
        }


        sendingButton.setOnClickListener {
            QuizStorage.setQuestions(question1, question2, question3)
            val results = QuizStorage.answer(personAnswers)
            val totalPoints = question1.points + question2.points + question3.points

            val bundle = Bundle().apply {
                putInt(ARG_PARAM1,results)
                putInt(ARG_PARAM2,totalPoints)
                putString(ARG_PARAM3, quizNames)
            }
            findNavController().navigate(R.id.action_QuizScreen_to_ResultScreen, args = bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAnimatioRadioButtonCheck(radioGroup: RadioGroup, radioButtonId: Int){
        val radioButtonCheck: RadioButton = radioGroup.findViewById(radioButtonId)

        val rotationX = PropertyValuesHolder.ofFloat(View.ROTATION_X, 0f, 360f)
        val textColor = PropertyValuesHolder.ofInt(
            "textColor",
            Color.parseColor("#FF000000"),
            Color.parseColor("#F44336"),
        ).apply {
            setEvaluator(ArgbEvaluator())
        }
        // вращение и изменение цвета
        ObjectAnimator.ofPropertyValuesHolder(radioButtonCheck, rotationX,textColor).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 2
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
        // просто вращение
//        ObjectAnimator.ofFloat(radioButtonCheck,
//            View.ROTATION_X,
//            0f,
//            360f
//        ).apply {
//            duration = 3000
//            interpolator = AccelerateDecelerateInterpolator()
//            repeatCount = 2
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()
    }
}