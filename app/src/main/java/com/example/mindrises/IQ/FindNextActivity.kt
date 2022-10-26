package com.example.mindrises.IQ

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mindrises.R
import kotlinx.android.synthetic.main.activity_find_next.*

class FindNextActivity : AppCompatActivity() {
    var findNextModel: FindNextModel?=null
    lateinit var listButtons:ArrayList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_next)
        findNextModel = ViewModelProvider(this).get(FindNextModel::class.java)

        listButtons= arrayListOf(ans1,ans2,ans3,ans4)
        findNextModel!!.currentQuestion.observe(this, Observer {
            val1.text=it.val1
            val2.text=it.val2
            val3.text=it.val3
            val4.text=it.val4
            ans1.text=it.ans1
            ans2.text=it.ans2
            ans3.text=it.ans3
            ans4.text=it.ans4
        })

        findNextModel!!.nbCorrect.observe(this,Observer{
            nb_correct.text=it.toString()
        })
        findNextModel!!.nbWrong.observe(this,Observer{
            nb_wrong.text=it.toString()
        })
        val intent=getIntent()
    }
    fun disableButtons(){
        listButtons.forEach {
            it.isClickable=false
        }
    }
    fun enableButtons(){
        listButtons.forEach {
            it.isClickable=true
        }
    }
    fun endGame(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("You solved ${findNextModel!!.nbCorrect.value} questions out of 10\n" +
                "do you want to try again")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    // START THE GAME!
                    findNextModel!!.reInit()
                    enableButtons()
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
        disableButtons()

    }
    fun init_button_colors(){
        listButtons.forEach{
            initColor(it)
        }

    }
    fun initColor(b: Button){
        b.setBackgroundColor(Color.BLUE)
    }
    fun colorCorrect(b:Button?){
        b?.setBackgroundColor(Color.GREEN)
    }
    fun colorCorrect(answer:String){
        colorCorrect(getAnswerButton(answer))

    }
    fun colorIncorrect(b:Button?){
        b?.setBackgroundColor(Color.RED)
    }
    fun colorIncorrect(answer:String){
        colorIncorrect(getAnswerButton(answer))

    }


    fun getAnswerButton(answer: String):Button?{
        listButtons.forEach{
            if(it.text.toString().equals(answer))return it
        }
        return null
    }
    fun verify_userAnser(userAnswer:String,correctAnswer:String){
        colorCorrect(correctAnswer)
        if(!(userAnswer.equals(correctAnswer)))colorIncorrect(userAnswer)
    }
    fun selectAnswer(view: View) {
        val b = view as Button
        val userAnswer=b.text.toString()
        val correctAnswer=findNextModel!!.getCorrectAnswer()
        verify_userAnser(userAnswer,correctAnswer)
        findNextModel!!.setAnserUser(userAnswer)
        disableButtons()
        Handler(Looper.getMainLooper()).postDelayed({
            init_button_colors()
            if(!(findNextModel!!.isEnded())){
                Toast.makeText(this, "next question", Toast.LENGTH_SHORT).show()
                findNextModel!!.nextQuestion()
                enableButtons()
            }

            else {endGame()}
        }, 1000)




    }
}