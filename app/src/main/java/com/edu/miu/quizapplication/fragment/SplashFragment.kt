package com.edu.miu.quizapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.edu.miu.quizapplication.R
import com.edu.miu.quizapplication.dao.DatabaseConnection
import com.edu.miu.quizapplication.model.Quiz
import com.edu.miu.quizapplication.utility.PreferencesManager
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    private lateinit var welcomeView : TextView
    private var preferencesManager : PreferencesManager?=null

    override fun onResume() {
        super.onResume()
        welcomeView.postDelayed({
            if(!preferencesManager?.isFirstTime()!!) {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
        }, 1000)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferencesManager = PreferencesManager(context)
        val view = inflater.inflate(R.layout.splash_fragment, container, false)
        welcomeView = view.findViewById(R.id.logo_welcome)
        addQuizzesToDB()
        return view
    }


    fun addQuizzesToDB() {

        val q1 = Quiz(1L, "What is a correct syntax to output \"Hello World\" in Kotlin?", "cout << \"Hello World\";", " System.out.printline(\"Hello World\")", " println(\"Hello World\")", " Console.WriteLine(\"Hello World\");", "d", "Explanation text is here!!")
        val q2 = Quiz(2L, "How do you insert COMMENTS in Kotlin code?", " # This is a comment", " /* This is a comment", " -- This is a comment", " // This is a comment", "d", "Explanation text is here!!")
        val q3 = Quiz(3L, "Which keyword is used to declare a function?", "fun", "decl", "define", "function", "a", "Explanation text is here!!")
        val q4 = Quiz(4L, "How can you create a variable with the numeric value 5?", "num = 5", "int num = 5", " num = 5 int", "val num = 5", "d", "Explanation text is here!!")
        val q5 = Quiz(5L, "How can you create a variable with the floating number 2.8?", " float num = 2.8", " double num = 2.8", " val num = 2.8", " num = 2.8 float", "c", "Explanation text is here!!")
        val q6 = Quiz(6L, "Question 6", "Choice A", "Choice B", "Choice C", "Choice D", "d", "Explanation text is here!!")
        val q7 = Quiz(7L, "Question 7", "Choice A", "Choice B", "Choice C", "Choice D", "b", "Explanation text is here!!")
        val q8 = Quiz(8L, "Question 8", "Choice A", "Choice B", "Choice C", "Choice D", "a", "Explanation text is here!!")
        val q9 = Quiz(9L, "Question 9", "Choice A", "Choice B", "Choice C", "Choice D", "a", "Explanation text is here!!")
        val q10 = Quiz(10L, "Question 10", "Choice A", "Choice B", "Choice C", "Choice D", "a", "Explanation text is here!!")
        val q11 = Quiz(11L, "Question 11", "Choice A", "Choice B", "Choice C", "Choice D", "b", "Explanation text is here!!")
        val q12 = Quiz(12L, "Question 12", "Choice A", "Choice B", "Choice C", "Choice D", "b", "Explanation text is here!!")
        val q13 = Quiz(13L, "Question 13", "Choice A", "Choice B", "Choice C", "Choice D", "d", "Explanation text is here!!")
        val q14 = Quiz(14L, "Question 14", "Choice A", "Choice B", "Choice C", "Choice D", "a", "Explanation text is here!!")
        val q15 = Quiz(15L, "Question 15", "Choice A", "Choice B", "Choice C", "Choice D", "c", "Explanation text is here!!")

        launch {
            context?.let {
                DatabaseConnection(it).getQuizDao().deleteAll()
                DatabaseConnection(it).getQuizDao().addAll(q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15)
            }
        }
    }
}