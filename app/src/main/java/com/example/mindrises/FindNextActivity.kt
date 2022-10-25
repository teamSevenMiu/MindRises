package com.example.mindrises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_find_next.*

class FindNextActivity : AppCompatActivity() {
    var findNextModel:FindFirstModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_next)
        findNextModel = ViewModelProvider(this).get(FindFirstModel::class.java)
        var question : MutableLiveData<QuestionFindFirst> = findNextModel!!.getFirstQuestion()
        question.observe(this, Observer {
            val1.text=it.val1
            val2.text=it.val2
            val3.text=it.val3
            val4.text=it.val4
            ans1.text=it.ans1
            ans2.text=it.ans2
            ans4.text=it.ans3
            ans4.text=it.ans4
        })
        val intent=getIntent()


    }

    fun selectAnswer(view: View) {
        if(!findNextModel!!.isEnded()){
            Toast.makeText(this, "next question", Toast.LENGTH_SHORT).show()
            findNextModel!!.getNextQuestion()
        }

        else Toast.makeText(this, "end of question", Toast.LENGTH_SHORT).show()
    }
}