package com.example.mindrises.math

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mindrises.R
import kotlinx.android.synthetic.main.activity_math1_detail.*

class Math1DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math1_detail)
        if(intent.hasExtra("your") && intent.hasExtra("correct")
            && intent.hasExtra("image")){
            var image =intent.getIntExtra("image",0)
            var your = intent.getStringExtra("your")
            var correct = intent.getStringExtra("correct")
            textView3.text = correct
            textView5.text = your
            imageView2.setImageResource(image)
        }
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

    }//end of function

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}