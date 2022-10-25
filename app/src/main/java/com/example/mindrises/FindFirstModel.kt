package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindFirstModel:ViewModel() {
    private var questions=ArrayList<QuestionFindFirst>(arrayListOf(QuestionFindFirst("11","112","113","114",
    "115","116","117","118","115"),
        QuestionFindFirst("3","4","5","6",
            "7","8","9","10","7"),
        QuestionFindFirst("13","14","15","16",
            "17","18","19","110","17"),
        QuestionFindFirst("3","4","5","6",
            "7","8","9","10","7"),
        QuestionFindFirst("13","14","15","16",
            "17","18","19","110","17"))
    )
    private var indexQuestion:Int=0;
    private var currentQuestion=MutableLiveData<QuestionFindFirst>()

    fun isEnded() = indexQuestion>=questions.size
    fun getFirstQuestion():MutableLiveData<QuestionFindFirst>{
        currentQuestion.value=questions.get(indexQuestion)
        return currentQuestion
    }
    fun getNextQuestion():MutableLiveData<QuestionFindFirst>{
        currentQuestion.value=questions.get(indexQuestion)
        if(indexQuestion<questions.size){
            indexQuestion++;
        }
        return currentQuestion
    }

}