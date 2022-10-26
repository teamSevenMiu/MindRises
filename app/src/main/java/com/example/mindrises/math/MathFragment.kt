package com.example.mindrises

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.databinding.FragmentMathBinding
import com.example.mindrises.databinding.ItemPuzzleBinding
import com.example.mindrises.math.Math1Level
import com.example.mindrises.math.Math2Level
import com.example.mindrises.math.Math3Level


class MathFragment : Fragment() {

    private var _binding: FragmentMathBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MathViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MathViewModel::class.java)

        _binding = FragmentMathBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = MathAdapter(requireContext(), viewModel.items.value ?: ArrayList())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

class MathAdapter(var context: Context, var list: ArrayList<PuzzleItem>) : RecyclerView.Adapter<MathAdapter.MyViewHolder>() {

    private lateinit var binding: ItemPuzzleBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MathAdapter.MyViewHolder {
        binding = ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MathAdapter.MyViewHolder, position: Int) {
        binding.title.text = list[position].name
        binding.image.setImageResource(list[position].image)



        holder.itemView.setOnClickListener {

            if (position == 0){
                val intent = Intent(context, Math1Level::class.java)
                context.startActivity(intent)
            }

            if (position == 1){
                val intent = Intent(context, Math2Level::class.java)
                context.startActivity(intent)
            }
            if (position == 2){
                val intent = Intent(context, Math3Level::class.java)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}