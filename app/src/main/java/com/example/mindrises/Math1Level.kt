package com.example.mindrises

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_math1_level.*

class Math1Level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math1_level)
        mg1.setOnClickListener(){
            var from=1
            var to=5
            var tTime:Long=5000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
        mg2.setOnClickListener(){
            var from=6
            var to=10
            var tTime:Long=10000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
        mg3.setOnClickListener(){
            var from=11
            var to=15
            var tTime:Long=15000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
        mg4.setOnClickListener(){
            var from=16
            var to=20
            var tTime:Long=20000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
        mg5.setOnClickListener(){
            var from=21
            var to=30
            var tTime:Long=25000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
        mg6.setOnClickListener(){
            var from=1
            var to=100
            var tTime:Long=35000
            val intent= Intent(this,Math1::class.java)
            intent.putExtra("from",from)
            intent.putExtra("to",to)
            intent.putExtra("tTime",tTime)
            this.startActivity(intent)
        }
//        button.setOnClickListener(){
//            val intent= Intent(this,Math1Level::class.java)
//            this.startActivity(intent)
//        }
//        button3.setOnClickListener(){
//           // val intent= Intent(this,Math2::class.java)
//           // this.startActivity(intent)
//        }
//        button4.setOnClickListener(){
//            //val intent= Intent(this,Math3::class.java)
//            //this.startActivity(intent)
//        }
    }
}