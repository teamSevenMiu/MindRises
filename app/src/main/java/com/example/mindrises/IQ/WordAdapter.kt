package com.example.mindrises.IQ

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.mindrises.databinding.LetterItemBinding

class WordAdapter (var context: Context, var list: ArrayList<String>) : RecyclerView.Adapter<WordAdapter.MyViewHolder>(){

    private lateinit var binding: LetterItemBinding
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int):
            WordAdapter.MyViewHolder {
            binding = LetterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.button.text=list[position]
    }
    override fun getItemCount() = list.size
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}