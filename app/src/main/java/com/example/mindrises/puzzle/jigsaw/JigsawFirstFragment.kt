package com.example.mindrises

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.databinding.FragmentJigsawFirstBinding
import com.example.mindrises.databinding.FragmentPuzzleBinding
import com.example.mindrises.databinding.ItemJigsawBinding
import com.example.mindrises.databinding.ItemPuzzleBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class JigsawFirstFragment : Fragment() {

    private var _binding: FragmentJigsawFirstBinding? = null

    private lateinit var viewModel: JigsawFirstViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        viewModel = ViewModelProvider(this).get(JigsawFirstViewModel::class.java)

        _binding = FragmentJigsawFirstBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = JigsawFirstAdapter(this,requireContext(), viewModel.items.value ?: ArrayList())


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class JigsawFirstAdapter(var fragment:Fragment,var context: Context, var list: ArrayList<JigsawItem>) : RecyclerView.Adapter<JigsawFirstAdapter.MyViewHolder>() {

    private lateinit var binding: ItemJigsawBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JigsawFirstAdapter.MyViewHolder {
        binding = ItemJigsawBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: JigsawFirstAdapter.MyViewHolder, position: Int) {
        binding.image.setImageResource(list[position].image)

        holder.itemView.setOnClickListener {


            val asset = list[position].name
            val piece = list[position].piece
            val bundle = bundleOf("asset" to asset, "piece" to piece)
            fragment.findNavController().navigate(R.id.action_jigsaw_first_second, bundle)
        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}