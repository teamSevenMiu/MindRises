package com.example.mindrises

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_math1_result.*

class Math1Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math1_result)
        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        val temp= args?.getSerializable("userResult") as  ArrayList<MathClass1>
        recyclerView1.layoutManager = GridLayoutManager(this,1, RecyclerView.VERTICAL,false)
        // Create an object for the MyAdapter
        val adapter = MyAdapter(this,temp)
        // Set adapter to your RecyclerView
        recyclerView1.adapter = adapter
    }
}