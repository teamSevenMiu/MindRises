package com.example.mindrises.IQ

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mindrises.R
import kotlinx.android.synthetic.main.activity_find_next.*
import kotlinx.android.synthetic.main.activity_find_word.*
import kotlinx.android.synthetic.main.activity_find_word.nb_correct
import kotlinx.android.synthetic.main.activity_find_word.nb_wrong

class FindWordActivity : AppCompatActivity() {

    var findWordModel: FindWordModel?=null
    var listButtons:ArrayList<Button>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_find_word)
        listButtons=arrayListOf<Button>(letter1,letter2,letter3,letter4,letter5,letter6,letter7,letter8)
        var b = gridLayout.get(0) as Button
        b.text="Z"

        findWordModel= ViewModelProvider(this).get(FindWordModel::class.java)
        var word_selected : MutableLiveData<String> = findWordModel!!.selectLetter("")
        initGridBlanck()
        selectButtons()
        show_letters()
        findWordModel!!.currentWord.observe(this, Observer {
            initGridBlanck()
            selectButtons()
            show_letters()

        })
        findWordModel!!.nbCorrect.observe(this,Observer{
            nb_correct.text=it.toString()
        })
        findWordModel!!.nbWrong.observe(this,Observer{
            nb_wrong.text=it.toString()
        })

        findWordModel!!.wordSelected.observe(this, Observer {
            selected_word.text=it.toString()
        })
        val intent=getIntent()

    }

    //the buttons to be shown in the screen
    fun selectButtons(){
        val randomList = (0..15).shuffled().take(findWordModel!!.currentWord.value!!.length)
        listButtons?.clear()
        randomList.forEach {
            listButtons?.add(gridLayout.get(it) as Button)
        }
    }

    //add letter to the buttons and make them visible with black color
    fun show_letters(){
        for(i in 0 until listButtons!!.size){
            listButtons!![i].text = ""+findWordModel!!.currentWord.value!!.get(i)
            setTextVisible(listButtons!![i])

        }
    }
    fun setTextInvisible(b:Button){
        b.setTextColor(Color.WHITE)
        b.isClickable=false
    }
    fun setAllTextVisible(){
        listButtons!!.forEach {
            setTextVisible(it)
        }
    }
    fun setTextVisible(b:Button){
        b.setTextColor(Color.BLACK)
        b.isClickable=true
    }
    fun initGridBlanck(){
        gridLayout.forEach {
            val b = it as Button
            b.setBackgroundColor(Color.WHITE)
            b.stateListAnimator=null
            setTextInvisible(b)
        }
    }
    fun selectChar(view:View){
        val v=view as Button
        setTextInvisible(v)
        this.findWordModel?.selectLetter(v.text.toString()) ?: Toast.makeText(this, "Error selected letter", Toast.LENGTH_SHORT).show()
        //Log.i("l1 : ${findWordModel!!.cu}")
        if(findWordModel!!.endSelection()){
            if (this.findWordModel!!.correctGuess()){
                answerCorrect()
                if(this.findWordModel!!.endGame())
                    endGame()
            }else {
                answerWrong()
            }
        }
    }

    fun answerWrong(){
        findWordModel!!.upCorrect()
        selected_word.setTextColor(Color.RED)
        Handler(Looper.getMainLooper()).postDelayed({
            setAllTextVisible()
            this.findWordModel?.reinit_selected_word() ?: Toast.makeText(this, "Error retry", Toast.LENGTH_SHORT).show()
            selected_word.setTextColor(Color.GRAY)
        }, 1000)

    }

    fun answerCorrect(){
        findWordModel!!.upCorrect()
        selected_word.setTextColor(Color.GREEN)
        Handler(Looper.getMainLooper()).postDelayed({
            this.findWordModel!!.reinit_selected_word()
            selected_word.setTextColor(Color.GRAY)
            this.findWordModel!!.nextWord()

        }, 1000)

    }
    fun endGame(){
        val builder = AlertDialog.Builder(this)
        refresh.isClickable=false
        builder.setMessage("You solved ${findWordModel!!.nbCorrect.value} questions out of 10\n" +
                "do you want to try again")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    // START THE GAME!
                    findWordModel!!.reInit()

                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
        Toast.makeText(this,"end game",Toast.LENGTH_LONG)
    }
    fun retry(view: View) {
        setAllTextVisible()
        this.findWordModel?.reinit_selected_word() ?: Toast.makeText(this, "Error retry", Toast.LENGTH_SHORT).show()
    }
}