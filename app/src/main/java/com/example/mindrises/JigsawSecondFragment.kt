package com.example.mindrises

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mindrises.databinding.FragmentJigsawSecondBinding
import java.util.*


class JigsawSecondFragment : Fragment() {

    private var _binding: FragmentJigsawSecondBinding? = null
    private lateinit var viewModel: JigsawSecondViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(JigsawSecondViewModel::class.java)
        _binding = FragmentJigsawSecondBinding.inflate(inflater, container, false)

        val layout = binding.layout
        val asset = arguments?.getString("asset") ?: ""

        val imageView = binding.imageView
        imageView.post(Runnable {
            viewModel.setupPeices(layout,imageView,requireContext(),asset)
        })

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}