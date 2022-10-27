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
import kotlinx.android.synthetic.main.activity_play_music.*

class MemorizeActivity : AppCompatActivity() {
    var list= arrayListOf<Int>(0,2,5)
    var patterModel:PatterModel?=null
    var nb_selected_buttons=0
    //var buttonMusic=HashMap<Button,MediaPlayer>()
    var buttons=ArrayList<Button>()
    fun patternCompleted():Boolean {return nb_selected_buttons==list.size}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorize)
        //buttonMusic.put(note1,MediaPlayer.create(this, R.raw.note1))
        buttons.add(note1)
        buttons.add(note2)
        buttons.add(note3)
        buttons.add(note4)
        buttons.add(note5)
        buttons.add(note6)
        buttons.add(note7)
        buttons.add(note8)
        buttons.add(note9)
        val intent=getIntent()
        init_colors()
        patterModel = ViewModelProvider(this).get(PatterModel::class.java)
        patterModel!!.pattern.observe(this, Observer{
            list.clear()
            nb_selected_buttons=0
            init_colors()
            it.forEach { list.add(it) }
            showListOfButtonToFind()
        })
        patterModel!!.nbCorrect.observe(this, Observer{
            nb_correct_game3.text=it.toString()
        })
        patterModel!!.nbWrong.observe(this, Observer{
            nb_wrong_game3.text=it.toString()
        })

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun disableButtons(){
        buttons.forEach {
            it.isClickable=false
        }
    }
    fun enableButtons(){
        buttons.forEach {
            it.isClickable=true
        }
    }
    fun checkCorrectAnswer(b:Button):Boolean{
        list.forEach{
            if(buttons[it].equals(b))return true
        }
        return false
    }
    fun showListOfButtonToFind(){
        disableButtons()
        colorSelectedList()
        Handler(Looper.getMainLooper()).postDelayed({
            init_colors()
            enableButtons()
        }, 3000)
    }
    fun colorSelectedList(){
        list.forEach {
            colorSelected(buttons[it])
        }
    }
    fun initColor(b: Button){
        b.setBackgroundColor(Color.GRAY)
    }
    fun colorSelected(b:Button){
        b.setBackgroundColor(Color.BLUE)
    }
    fun colorError(b:Button){
        b.setBackgroundColor(Color.RED)
    }
    fun init_colors(){
        buttons.forEach { initColor(it) }
    }


    fun selectButton(view: View) {
        val b=view as Button
        nb_selected_buttons++
        if(checkCorrectAnswer(b)){

            colorSelected(b)
            if(patternCompleted()){
                patterModel!!.addCorrect()
                patterModel!!.nextPatter()
                Toast.makeText(this,"new pattern",Toast.LENGTH_LONG)
            }

        }

        else {
            patterModel!!.addWrong()
            colorError(b)
            colorSelectedList()
            disableButtons()
            Handler(Looper.getMainLooper()).postDelayed({
                patterModel!!.nextPatter()
                enableButtons()
            }, 1000)

        }

        if(patterModel!!.endPatters()){

        }
        //val playback=buttonMusic.get(b)
        // playback?.start()
    }

    fun endGame(){

        val builder = AlertDialog.Builder(this)
        disableButtons()
        builder.setMessage("You solved ${patterModel!!.nbCorrect.value} questions out of 10\n" +
                "do you want to try again")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    // START THE GAME!
                    patterModel!!.reinit()
                    enableButtons()
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()


    }
}