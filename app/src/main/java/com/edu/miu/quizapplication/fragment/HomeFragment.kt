package com.edu.miu.quizapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.edu.miu.quizapplication.R
import com.edu.miu.quizapplication.dao.DatabaseConnection
import com.edu.miu.quizapplication.model.HomeViewModel
import com.edu.miu.quizapplication.model.Quiz
import com.edu.miu.quizapplication.utility.ApplicationUtility
import com.edu.miu.quizapplication.utility.toast
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    private lateinit var question: TextView
    private lateinit var score: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var questions: List<Quiz>
    private var homeViewModel: HomeViewModel? = null
    private var selected: String? = null
    private var answers: MutableList<String> = mutableListOf()
    private lateinit var theQuiz: Quiz
    private var isFirstTime = true
    private var questionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        val skipButton = view.findViewById<Button>(R.id.btn_qstn_skip)
        val nextButton = view.findViewById<Button>(R.id.btn_qstn_next)
        question = view.findViewById(R.id.question)
        score = view.findViewById(R.id.score)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val liveScore : MutableLiveData<Int> = homeViewModel!!.getInitialScore()
        liveScore.observe(viewLifecycleOwner) {
            score.text = String.format("%d/15", it)
        }
        launch {
            context?.let {
                questions = DatabaseConnection(it).getDao().getAll()
//TODO
            }

        }

        skipButton.setOnClickListener {
            //TODO
        }

        nextButton.setOnClickListener {
            if(selected != null) {
                //TODO
            } else {
                context?.toast(getString(R.string.please_provide_answer_toast_message))
            }
        }

        radioGroup = view.findViewById(R.id.question_radio)
        radioGroup.setOnCheckedChangeListener(this::radioButtonClickHandler)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun radioButtonClickHandler(group: RadioGroup, id: Int) {
        when(id) {
            R.id.radio_q1_a -> selected = ApplicationUtility.Choices.A.value
            R.id.radio_q1_b -> selected = ApplicationUtility.Choices.B.value
            R.id.radio_q1_c -> selected = ApplicationUtility.Choices.C.value
            R.id.radio_q1_d -> selected = ApplicationUtility.Choices.D.value
        }
    }

    private fun calculate(answer: String) {
        if(theQuiz.answer == answer) {
            homeViewModel!!.getCurrentScore()
        }
    }

    private fun changeQuestion(view:View) {
        if(!isFirstTime) {
            val selectedAnswer =
                if(selected != null) selected else ""
            answers.add(selectedAnswer!!)
        }
        isFirstTime = false

        if(questionIndex == 15) {
            val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(
                score = homeViewModel?.getFinalScore()?.value!!, answers = answers.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }

        theQuiz = questions[questionIndex]
        question.text = theQuiz.question
        val radioGroup = view.findViewById(R.id.question_radio) as RadioGroup
        val choices = listOf(theQuiz.a, theQuiz.b, theQuiz.c, theQuiz.d)

        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = choices[i]
        }
        questionIndex++
        selected = null
        radioGroup.clearCheck()
    }

}