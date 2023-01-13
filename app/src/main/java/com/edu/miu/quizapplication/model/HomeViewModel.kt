package com.edu.miu.quizapplication.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private var score = 0
    private var liveScore = MutableLiveData<Int>()

    fun getCurrentScore() {
        score += 1
        liveScore.value = score
    }

    fun getInitialScore(): MutableLiveData<Int> {
        liveScore.value = score
        return liveScore
    }

    fun getFinalScore() : MutableLiveData<Int> {
        return liveScore
    }
}