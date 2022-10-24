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
import com.example.mindrises.databinding.FragmentPuzzleBinding
import com.example.mindrises.databinding.ItemPuzzleBinding

class PuzzleFragment : Fragment() {

    private var _binding: FragmentPuzzleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(PuzzleViewModel::class.java)

        _binding = FragmentPuzzleBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = PuzzleAdapter(requireContext(), viewModel.items.value ?: ArrayList())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

class PuzzleAdapter(var context: Context,var list: ArrayList<PuzzleItem>) : RecyclerView.Adapter<PuzzleAdapter.MyViewHolder>() {

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
               val intent = Intent(context,MagicActivity::class.java)
               context.startActivity(intent)
           }
        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}