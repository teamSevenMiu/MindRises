package com.example.mindrises.math

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import com.example.mindrises.R
import kotlinx.android.synthetic.main.activity_math1.*
import kotlin.random.Random

class Math1 : AppCompatActivity() {
    var x:Int=1
    var y:Int=1
    var index:Int=1
    var z:String=""
    var result1:Int=1
    var btnShowResult:Int=1
    var list= arrayListOf<String>("/","*","+","-")
    var correct:Int=0
    var wrong:Int=0
    lateinit var timer2:CountDownTimer
    var sLevel:Int=1
    var eLevel:Int=10
    var tTime:Long=10000
    val userResult = ArrayList<MathClass1>()
    var numberQ:Int=5
    var image = intArrayOf(
        R.drawable.check1,
        R.drawable.cross,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math1)
        sLevel=intent.getIntExtra("from",1)
        eLevel=intent.getIntExtra("to",10)
        tTime=intent.getLongExtra("tTime",10000)
        numberQ=intent.getIntExtra("numberQ",5)
        generateQuestion(sLevel,eLevel)
        setButtonColor()
        getSomeTime(tTime)
        button2.setOnClickListener(){
            val intent= Intent(this, Math1Level::class.java)
            this.startActivity(intent)
            finish()
        }
        btn1.setOnClickListener(){
            var a=Integer.parseInt(btn1.text.toString())
            timer2.cancel()
            if(a==result1){
                correct++
                txtCorrect.text=""+correct
                btn1.setBackgroundColor(Color.GREEN)
                saveData("$x$z$y=$result1","$x$z$y=$result1",image[0])
                Handler(Looper.getMainLooper()).postDelayed({
                    btn1.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                wrong++
                txtWrong.text=""+wrong
                saveData("$x$z$y=$a","$x$z$y=$result1",image[1])
                btn1.setBackgroundColor(Color.RED)
                showCorrectButton(btnShowResult)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }
            if(finishExam()){
                startReport()
            }
        }
        btn2.setOnClickListener(){
            var a=Integer.parseInt(btn2.text.toString())
            timer2.cancel()
            if(a==result1){
                saveData("$x$z$y=$result1","$x$z$y=$result1",image[0])
                correct++
                txtCorrect.text=""+correct
                btn2.setBackgroundColor(Color.GREEN)
                Handler(Looper.getMainLooper()).postDelayed({
                    btn2.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                saveData("$x$z$y=$a","$x$z$y=$result1",image[1])
                wrong++
                txtWrong.text=""+wrong
                btn2.setBackgroundColor(Color.RED)
                showCorrectButton(btnShowResult)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }
            if(finishExam()){
                startReport()
            }
        }
        btn3.setOnClickListener(){
            var a=Integer.parseInt(btn3.text.toString())
            timer2.cancel()
            if(a==result1){
                saveData("$x$z$y=$result1","$x$z$y=$result1",image[0])
                correct++
                txtCorrect.text=""+correct
                btn3.setBackgroundColor(Color.GREEN)
                Handler(Looper.getMainLooper()).postDelayed({
                    btn3.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                saveData("$x$z$y=$a","$x$z$y=$result1",image[1])
                wrong++
                txtWrong.text=""+wrong
                btn3.setBackgroundColor(Color.RED)
                showCorrectButton(btnShowResult)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }
            if(finishExam()){
                startReport()
            }
        }
        btn4.setOnClickListener(){
            var a=Integer.parseInt(btn4.text.toString())
            timer2.cancel()
            if(a==result1){
                saveData("$x$z$y=$result1","$x$z$y=$result1",image[0])
                correct++
                txtCorrect.text=""+correct
                btn4.setBackgroundColor(Color.GREEN)
                Handler(Looper.getMainLooper()).postDelayed({
                    btn4.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                saveData("$x$z$y=$a","$x$z$y=$result1",image[1])
                wrong++
                txtWrong.text=""+wrong
                btn4.setBackgroundColor(Color.RED)
                showCorrectButton(btnShowResult)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }
            if(finishExam()){
                startReport()
            }
        }
    }
    private fun generateQuestion( s:Int, e:Int):Unit{
        index= Random.nextInt(list.size)
        z= list[index].toString()
        x= Random.nextInt(s,e)
        y= Random.nextInt(s,e)
        if(z=="/"){
            while(x%y!=0){
                y= Random.nextInt(1,10)
            }
        }
        txt1.text="$x$z$y="
        result1= when(z){
            "*"->x*y
            "/"->x/y
            "+"->x+y
            "-"->x-y
            else-> 0
        }
        var a1=result1-1
        var a2=Random.nextInt((result1+1),(result1+3))
        var a3=a2+1
        var a4=a2+2
        btn1.text = "$a1"
        btn2.text = "$a2"
        btn3.text = "$a3"
        btn4.text = "$a4"
        btnShowResult=Random.nextInt(1,5)
        when(btnShowResult){
            1->btn1.text="$result1"
            2->btn2.text="$result1"
            3->btn3.text="$result1"
            else->{
                btn4.text="$result1"
            }
        }
    }//end of function

    private fun getSomeTime(time:Long){
        timer2= object : CountDownTimer(time, 1000) {
            override fun onTick(millis: Long) {
                timer1.text =  ""+(millis/1000)
            }
            override fun onFinish() {
                timer1.text = "Time finish!"
                wrong++
                txtWrong.text=""+wrong
                showCorrectButton(btnShowResult)
                saveData("Time up","$x$z$y=$result1",image[1])
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
                if(finishExam()){
                    startReport()
                }
            }
        }
        timer2.start()
    }//end of time function
    private fun setButtonColor(){
        btn1.setBackgroundColor(Color.BLUE)
        btn2.setBackgroundColor(Color.BLUE)
        btn3.setBackgroundColor(Color.BLUE)
        btn4.setBackgroundColor(Color.BLUE)
    }
    private fun showCorrectButton(x:Int){
        when(x){
            1->  btn1.setBackgroundColor(Color.GREEN)
            2->  btn2.setBackgroundColor(Color.GREEN)
            3->  btn3.setBackgroundColor(Color.GREEN)
            4->  btn4.setBackgroundColor(Color.GREEN)
        }
    }

    //val answer: String, val correct: String,val image:Int
    private fun saveData(answer:String,correct:String,image:Int){
        val x= MathClass1(answer,correct,image)
        userResult.add(x)
    }
    private fun finishExam():Boolean{
        var a:Int=0
        var b:Int=0
        if(txtCorrect.text.toString()!=""){
            a=Integer.parseInt(txtCorrect.text.toString())
        }
        if(txtWrong.text.toString()!=""){
            b=Integer.parseInt(txtWrong.text.toString())
        }
        if(a+b==numberQ){
            timer2.cancel()
            return true
        }
        return false
    }
    private fun startReport(){
        val intent=Intent(this, Math1Result::class.java)
        val args = Bundle()
        args.putSerializable("userResult", userResult)
        intent.putExtra("BUNDLE", args)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        timer2.cancel()
    }

    override fun onPause() {
        super.onPause()
        timer2.cancel()
    }
}//end of class