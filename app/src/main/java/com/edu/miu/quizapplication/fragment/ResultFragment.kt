package com.edu.miu.quizapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.edu.miu.quizapplication.R

class ResultFragment : BaseFragment() {

    private lateinit var scoreView: TextView
    private lateinit var resultButton: Button
    private lateinit var tryAgainButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_fragment, container, false)
        scoreView = view.findViewById(R.id.score)
        val score = ResultFragmentArgs.fromBundle(requireArguments()).score
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        val countWrongAnswers = 15 - score
        val finalScore = "$score/15"
        val result = String.format(
            "Total Questions: 15\n\nCorrect Answers(Score): %d\n\nWrong Answer: %d\n\nYour Score is: %s",
            score, countWrongAnswers, finalScore
        )
        scoreView.text = result
        resultButton = view.findViewById(R.id.btn_result_analysis)
        tryAgainButton = view.findViewById(R.id.btn_try_again)

        tryAgainButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_resultFragment_to_homeFragment)
        }

        resultButton.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAnswerFragment(answers)
            Navigation.findNavController(requireView()).navigate(action)
        }

        return view
    }
}