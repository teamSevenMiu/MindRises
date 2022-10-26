package com.example.mindrises.math

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import com.example.mindrises.R
import kotlinx.android.synthetic.main.activity_math2.btn1
import kotlinx.android.synthetic.main.activity_math2.btn2
import kotlinx.android.synthetic.main.activity_math2.button2
import kotlinx.android.synthetic.main.activity_math2.timer1
import kotlinx.android.synthetic.main.activity_math2.txt1
import kotlinx.android.synthetic.main.activity_math2.txtCorrect
import kotlinx.android.synthetic.main.activity_math2.txtWrong
import kotlin.random.Random

class Math2 : AppCompatActivity() {
    var x:Int=1
    var y:Int=1
    var index:Int=1
    var z:String=""
    var result1:Int=1
    var result2:Int=1
    var btnShowResult:Int=1
    var list= arrayListOf<String>("/","*","+","-")
    var wrong:Int=0
    lateinit var timer2:CountDownTimer
    var sLevel:Int=1
    var eLevel:Int=10
    var tTime:Long=10000
    var correct:Int=0
    var numberQ=5
    val userResult = ArrayList<MathClass1>()
    var image = intArrayOf(
        R.drawable.check1,
        R.drawable.cross,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math2)
        sLevel=intent.getIntExtra("from",1)
        eLevel=intent.getIntExtra("to",10)
        tTime=intent.getLongExtra("tTime",10000)
        numberQ=intent.getIntExtra("numberQ",5)
        generateQuestion(sLevel,eLevel)
        setButtonColor()
        getSomeTime(tTime)
        button2.setOnClickListener(){
            val intent= Intent(this, Math2Level::class.java)
            this.startActivity(intent)
            finish()
        }
        btn1.setOnClickListener(){
            var a=btnShowResult
            timer2.cancel()
            if(a==1){
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
            var a=btnShowResult
            timer2.cancel()
            if(a==2){
                correct++
                txtCorrect.text=""+correct
                btn2.setBackgroundColor(Color.GREEN)
                saveData("$x$z$y=$result1","$x$z$y=$result1",image[0])
                Handler(Looper.getMainLooper()).postDelayed({
                    btn2.setBackgroundColor(Color.BLUE)
                    generateQuestion(sLevel,eLevel)
                    getSomeTime(tTime)
                }, 1000)
            }else{
                wrong++
                txtWrong.text=""+wrong
                btn2.setBackgroundColor(Color.RED)
                showCorrectButton(btnShowResult)
                saveData("$x$z$y=$a","$x$z$y=$result1",image[1])
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
        result2=result1+1
        btnShowResult= Random.nextInt(1,3)
        when(btnShowResult){
            1->txt1.text=txt1.text.toString()+result1
            2->txt1.text=txt1.text.toString()+result2
        }
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

    }//end of function

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getSomeTime(x:Long){
        timer2= object : CountDownTimer(x, 1000) {
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
    }
    private fun showCorrectButton(x:Int){
        when(x){
            1->  btn1.setBackgroundColor(Color.GREEN)
            2->  btn2.setBackgroundColor(Color.GREEN)
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
    //val answer: String, val correct: String,val image:Int
    private fun saveData(answer:String,correct:String,image:Int){
        val x= MathClass1(answer,correct,image)
        userResult.add(x)
    }
    private fun startReport(){
        val intent=Intent(this, Math1Result::class.java)
        val args = Bundle()
        args.putSerializable("userResult", userResult)
        intent.putExtra("BUNDLE", args)
        startActivity(intent)
        finish()
    }
}