package com.example.mindrises.puzzle

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.PuzzleItem
import com.example.mindrises.databinding.ItemPuzzleBinding
import com.example.mindrises.puzzle.jigsaw.JigsawActivity
import com.example.mindrises.puzzle.magic.MagicActivity

class PuzzleAdapter(var context: Context, var list: ArrayList<PuzzleItem>) : RecyclerView.Adapter<PuzzleAdapter.MyViewHolder>() {

    private lateinit var binding: ItemPuzzleBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PuzzleAdapter.MyViewHolder {
        binding = ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PuzzleAdapter.MyViewHolder, position: Int) {
        binding.title.text = list[position].name
        binding.image.setImageResource(list[position].image)



        holder.itemView.setOnClickListener {

            if (position == 0){
                val intent = Intent(context, MagicActivity::class.java)
                context.startActivity(intent)
            }

            if (position == 1){
                val intent = Intent(context, JigsawActivity::class.java)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}