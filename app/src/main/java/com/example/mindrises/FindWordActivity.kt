package com.example.mindrises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_find_word.*

class FindWordActivity : AppCompatActivity() {
    var findWordModel:FindWordModel?=null
    var array:ArrayList<Button>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_word)
        array=arrayListOf<Button>(letter1,letter2,letter3,letter4,letter5,letter6,letter7,letter8)
        findWordModel= ViewModelProvider(this).get(FindWordModel::class.java)
        var word_selected : MutableLiveData<String> = findWordModel!!.selectLetter("")
        word_selected.observe(this, Observer {
            selected_word.text=it.toString()
        })
        val intent=getIntent()
    }
    fun selectChar(view:View){
        val v=view as Button
        v.isClickable=false

        this.findWordModel?.selectLetter(v.text.toString()) ?: Toast.makeText(this, "Error selected letter", Toast.LENGTH_SHORT).show()


    }

    fun retry(view: View) {
        array?.forEach{it.isClickable=true}
        this.findWordModel?.reinit_selected_word() ?: Toast.makeText(this, "Error retry", Toast.LENGTH_SHORT).show()

    }
}