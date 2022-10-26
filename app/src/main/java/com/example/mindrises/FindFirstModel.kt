package com.example.mindrises

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FindFirstModel:ViewModel() {
    private var questions=ArrayList<QuestionFindFirst>()
    fun generateEasyQuestions(){
        questions.clear()


        for(i in 0..9){
            val startValue= Random.nextInt(i*2+1,(i+1)*2+1)
            val step=Random.nextInt(i*2+1,(i+1)*2+1)
            val v1=startValue
            val v2=v1+step
            val v3=v2+step
            val v4=v3+step
            val correct=v4+step

            var s = ArrayList<Int>()
            s.clear()

            s.add(correct)
            Log.i("correct is ",correct.toString())
            for(i in 0..2){
                if(Random.nextBoolean()){
                    s.add(correct+Random.nextInt(i*2+1,(i+1)*2+1))
                }else s.add(correct-Random.nextInt(i*2+1,(i+1)*2+1))
            }

            s.shuffle()
            Log.i("-------","*******")
            Log.i("size of set is : ${s}","=====----=======")
            Log.i("${s[0]},${s[1]},${s[2]},${s[3]},${correct}","=")
            questions.add(QuestionFindFirst(v1.toString(),v2.toString(),v3.toString(),v4.toString(),s[0].toString(),s[1].toString(),s[2].toString(),s[3].toString(),correct.toString()))


        }
    }
    fun generateQuestions(difficulty:Int){

    }
    fun reInit(){
        generateEasyQuestions()
        currentQuestion=getFirstQuestion()
        userAnswersList.clear()
        nbCorrect.value=0
        nbWrong.value=0
        totalAnswers.value=0
        indexQuestion=0
    }
    lateinit var userAnswersList:ArrayList<String>
    private var indexQuestion:Int=0;
    var currentQuestion=MutableLiveData<QuestionFindFirst>()
    var nbCorrect=MutableLiveData<Int>()
    var nbWrong=MutableLiveData<Int>()
    var totalAnswers=MutableLiveData<Int>()

    init {
        generateEasyQuestions()

        currentQuestion=getFirstQuestion()
        userAnswersList=ArrayList<String>()
        nbCorrect.value=0
        nbWrong.value=0
        totalAnswers.value=0
        indexQuestion=0

    }
    fun setAnserUser( answer:String){
        userAnswersList.add(answer)
        if(answer.equals(currentQuestion.value!!.correct)) nbCorrect.value=nbCorrect.value!!+1
        else nbWrong.value=nbWrong.value!!+1
        totalAnswers.value?.plus(1)


    }
    fun isEnded():Boolean {
        Log.i(indexQuestion.toString(),"not indexqution")
        Log.i("size"+questions.size.toString(),"no size")
        return indexQuestion>=questions.size-1}
    fun getFirstQuestion():MutableLiveData<QuestionFindFirst>{
        currentQuestion.value=questions.get(indexQuestion)
        return currentQuestion
    }
    fun getCorrectAnswer():String{
        return currentQuestion.value?.correct ?: "-1"
    }
    fun nextQuestion(){
        if(indexQuestion<questions.size){
            indexQuestion++;
        }
        currentQuestion.value=questions.get(indexQuestion)

    }


}