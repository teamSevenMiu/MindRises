package com.example.mindrises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_math3_level.*

class Math3Level : AppCompatActivity() {
    var numberQ:Int=5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math3_level)
        mg1.setOnClickListener(){
            showMath3(1,5,5000,numberQ)
        }
        mg2.setOnClickListener(){
            showMath3(6,10,10000,numberQ)
        }
        mg3.setOnClickListener(){
            showMath3(11,15,15000,numberQ)
        }
        mg4.setOnClickListener(){
            showMath3(16,20,20000,numberQ)
        }
        mg5.setOnClickListener(){
            showMath3(21,30,25000,numberQ)
        }
        mg6.setOnClickListener(){
            showMath3(31,100,35000,numberQ)
        }
    }
    private fun showMath3(from:Int,to:Int,tTime:Long,numberQ:Int){
        val intent= Intent(this,Math3::class.java)
        intent.putExtra("from",from)
        intent.putExtra("to",to)
        intent.putExtra("tTime",tTime)
        intent.putExtra("numberQ",numberQ)
        this.startActivity(intent)
    }
}