package com.example.mindrises

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.databinding.FragmentMagicFirstBinding
import com.example.mindrises.databinding.ItemMagicBinding

class MagicFirstFragment : Fragment() {

    private var _binding: FragmentMagicFirstBinding? = null
    private lateinit var viewModel: MagicFirstViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(MagicFirstViewModel::class.java)

        _binding = FragmentMagicFirstBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = MagicFirstAdapter(this,requireContext(), viewModel.items.value ?: ArrayList())
        return binding.root
    }


}

class MagicFirstAdapter(var fragment:Fragment, var context: Context, var list: ArrayList<MagicItem>) : RecyclerView.Adapter<MagicFirstAdapter.MyViewHolder>() {

    private lateinit var binding: ItemMagicBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MagicFirstAdapter.MyViewHolder {
        binding = ItemMagicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MagicFirstAdapter.MyViewHolder, position: Int) {
        binding.image.setImageResource(list[position].image)
        binding.title.text = list[position].name

        holder.itemView.setOnClickListener {

            val size = list[position].size
            val bundle = bundleOf("size" to size)
            fragment.findNavController().navigate(R.id.action_magic_first_second, bundle)
        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}