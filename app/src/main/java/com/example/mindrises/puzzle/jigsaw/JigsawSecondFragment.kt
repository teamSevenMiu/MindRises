package com.example.mindrises.puzzle.jigsaw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mindrises.databinding.FragmentJigsawSecondBinding
import com.example.mindrises.main.Presets
import com.google.android.material.snackbar.Snackbar


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
        viewModel.pieceCount = arguments?.getInt("piece") ?: 4
        viewModel.gameOver = {
            gameOver()
        }

        _binding = FragmentJigsawSecondBinding.inflate(inflater, container, false)

        binding.reset.setOnClickListener {
            reset()
        }

        initGame()
        return binding.root

    }

    fun initGame(){


        binding.konfettiView.stopGracefully()
        binding.reset.isVisible = false

        val layout = binding.layout
        val asset = arguments?.getString("asset") ?: ""

        val imageView = binding.imageView
        imageView.post(Runnable {
            viewModel.setupPeices(layout,imageView,requireContext(),asset)
        })

    }

    fun reset(){
        binding.layout.removeAllViews()
        initGame()
    }

    private fun gameOver(){

        binding.reset.isVisible = true
        binding.reset.bringToFront()
        binding.konfettiView.start(Presets.festive())

        val snack = Snackbar.make(binding.parentContainer,"Congratulations ! You have Completed the Jigsaw Puzzle",
            Snackbar.LENGTH_LONG)
        snack.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}