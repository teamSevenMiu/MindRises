package com.example.mindrises.math

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.R
import kotlinx.android.synthetic.main.itemview.view.*

class MyAdapter (var context: Context, var list:ArrayList<MathClass1>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.image1.setImageResource(list[position].image)
        holder.itemView.item.text= list[position].answer
        holder.itemView.image1.setOnClickListener(){
            val intent = Intent(context, Math1DetailActivity::class.java)
            intent.putExtra("image", list[position].image)
            intent.putExtra("your", list[position].answer)
            intent.putExtra("correct",list[position].correct)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
