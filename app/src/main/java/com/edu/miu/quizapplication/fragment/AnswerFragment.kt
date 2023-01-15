package com.edu.miu.quizapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.edu.miu.quizapplication.R
import com.edu.miu.quizapplication.dao.DatabaseConnection
import com.edu.miu.quizapplication.model.Quiz
import kotlinx.coroutines.launch

class AnswerFragment : BaseFragment(){

    private lateinit var questions:List<Quiz>

    private fun result(questions: List<Quiz>, answers: List<String>):List<String> {
        val items = mutableListOf<String>()
        questions.forEachIndexed {
            index, quiz ->
            val item = String.format("Question: %s\n Your Answer: %s\n Correct answer: %s\n" +
                    " Explanation: %s", quiz.question, answers[index], quiz.answer, quiz.explanation)
            items.add(item)
        }
        return items
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.answer_fragment, container, false)
        val listView = view.findViewById<ListView>(R.id.list_view)
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers

        launch {
            context?.let {
                questions = DatabaseConnection(it).getQuizDao().getAll()
                questions.forEach {
                    question -> question.answer
                }
                listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, result(questions, answers.toList()))
            }
        }

        return view
    }
}