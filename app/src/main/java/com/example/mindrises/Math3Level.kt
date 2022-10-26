package com.example.mindrises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_math1_level.*
import kotlinx.android.synthetic.main.activity_math3_level.mg1
import kotlinx.android.synthetic.main.activity_math3_level.mg2
import kotlinx.android.synthetic.main.activity_math3_level.mg3
import kotlinx.android.synthetic.main.activity_math3_level.mg4
import kotlinx.android.synthetic.main.activity_math3_level.mg5
import kotlinx.android.synthetic.main.activity_math3_level.mg6

class Math3Level : AppCompatActivity() {
    var numberQ:Int=5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math3_level)
        val adapter= ArrayAdapter.createFromResource(this, R.array.numbers,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p0 != null) {
                    Toast.makeText(p0.context,p0.getItemAtPosition(p2).toString(), Toast.LENGTH_SHORT).show()
                    numberQ=  when(p0.getItemAtPosition(p2).toString()){
                        "Five"->5
                        "Ten"->10
                        "Fifteen"->15
                        "Twenty"->15
                        "Twenty-Five"->15
                        "Thirty"->30
                        "Thirty-Five"->35
                        "Forty"->40
                        "Fifty"->50
                        "Hundred"->100
                        else-> 200
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
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