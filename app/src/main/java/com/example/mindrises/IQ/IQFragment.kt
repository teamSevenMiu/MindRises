package com.example.mindrises

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindrises.IQ.FindNextActivity
import com.example.mindrises.IQ.FindWordActivity
import com.example.mindrises.IQ.IQViewModel
import com.example.mindrises.IQ.MemorizeActivity
import com.example.mindrises.databinding.FragmentIqBinding
import com.example.mindrises.databinding.ItemPuzzleBinding

class IQFragment : Fragment() {
    private var _binding: FragmentIqBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = IQFragment()
    }

    private lateinit var viewModel: IQViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(IQViewModel::class.java)

        _binding = FragmentIqBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager  = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = IQAdapter(requireContext(), viewModel.items.value ?: ArrayList())

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        viewModel = ViewModelProvider(this).get(IQViewModel::class.java)
        //Toast.makeText(this.context,"created ...",Toast.LENGTH_LONG).show()
        val view: View =inflater.inflate(R.layout.fragment_iq, container, false)
        view.startFind.setOnClickListener { view ->
            val intent = Intent(this.context, FindNextActivity::class.java)
            startActivity(intent)
        }
        view.findWord.setOnClickListener { view ->

            val intent = Intent(this.context, FindWordActivity::class.java)
            startActivity(intent)
        }

        view.playMusic.setOnClickListener { view ->
            //Toast.makeText(this.context,"Before sending ...",Toast.LENGTH_LONG).show()
            val intent = Intent(this.context, PlayMusicActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}*/
}
class IQAdapter(var context: Context, var list: ArrayList<PuzzleItem>) : RecyclerView.Adapter<IQAdapter.MyViewHolder>() {

    private lateinit var binding: ItemPuzzleBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IQAdapter.MyViewHolder {
        binding = ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: IQAdapter.MyViewHolder, position: Int) {
        binding.title.text = list[position].name
        binding.image.setImageResource(list[position].image)
        holder.itemView.setOnClickListener {

            if (position == 0){
                val intent = Intent(context, FindNextActivity::class.java)
                context.startActivity(intent)
            }

            if (position == 1){
                val intent = Intent(context, FindWordActivity::class.java)
                context.startActivity(intent)
            }
            if (position == 2){
                val intent = Intent(context, MemorizeActivity::class.java)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount() = list.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}