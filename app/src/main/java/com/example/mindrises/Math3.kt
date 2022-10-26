package com.example.mindrises

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_math3.*
import kotlinx.android.synthetic.main.activity_math3.btn1
import kotlinx.android.synthetic.main.activity_math3.button2
import kotlinx.android.synthetic.main.activity_math3.timer1
import kotlinx.android.synthetic.main.activity_math3.txt1
import kotlinx.android.synthetic.main.activity_math3.txtCorrect
import kotlinx.android.synthetic.main.activity_math3.txtWrong
import kotlin.random.Random

class Math3 : AppCompatActivity() {
    var x:Int=1
    var y:Int=1
    var index:Int=1
    var z:String=""
    var result1:Int=1
    var result2:Int=1
    var btnShowResult:Int=1
    var list= arrayListOf<String>("/","*","+","-")
    var wrong:Int=0
    lateinit var timer2: CountDownTimer
    var sLevel:Int=1
    var eLevel:Int=10
    var tTime:Long=10000
    var correct:Int=0
    var numberQ=5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math3)
        sLevel=intent.getIntExtra("from",1)
        eLevel=intent.getIntExtra("to",10)
        tTime=intent.getLongExtra("tTime",10000)
        numberQ=intent.getIntExtra("numberQ",5)
        generateQuestion(sLevel,eLevel)
        setButtonColor()
        getSomeTime(tTime)
        button2.setOnClickListener(){
            val intent= Intent(this,Math3Level::class.java)
            this.startActivity(intent)
        }
        btn1.setOnClickListener(){
            submit()
        }
        editTextNumber.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                submit()
            }
            false
        })
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
    }//end of function

    private fun getSomeTime(x:Long){
        timer2= object : CountDownTimer(x, 1000) {
            override fun onTick(millis: Long) {
                timer1.text =  ""+(millis/1000)
            }
            override fun onFinish() {
                timer1.text = "Time finish!"
                wrong++
                txtWrong.text=""+wrong
                //showCorrectButton(btnShowResult)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }
        }
        timer2.start()
    }//end of time function

    private fun setButtonColor(){
        btn1.setBackgroundColor(Color.BLUE)
    }
    private fun submit(){
        if(editTextNumber.text.isEmpty()|| editTextNumber.text.toString() == ""){
            Toast.makeText(this,"Please type your answer",Toast.LENGTH_SHORT).show()
        }else{
            var a=Integer.parseInt(editTextNumber.text.toString())
            timer2.cancel()
            if(a==result1){
                correct++
                txtCorrect.text=""+correct
                btn1.setBackgroundColor(Color.GREEN)
                Handler(Looper.getMainLooper()).postDelayed({
                    btn1.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                wrong++
                txtWrong.text=""+wrong
                btn1.setBackgroundColor(Color.RED)
                Handler(Looper.getMainLooper()).postDelayed({
                    setButtonColor()
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)

            }//end of else
            editTextNumber.setText("")
        }
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
        return a+b==numberQ
    }
}