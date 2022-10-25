package com.example.mindrises.puzzle.magic

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mindrises.databinding.FragmentMagicSecondBinding
import com.example.mindrises.main.Presets
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class MagicSecondFragment : Fragment() {

    private var _binding: FragmentMagicSecondBinding? = null
    private lateinit var viewModel: MagicSecondViewModel
    private var buttons = mutableListOf<Button>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val size = arguments?.getInt("size", 0) ?: 0
        viewModel = ViewModelProvider(this).get(MagicSecondViewModel::class.java)
        viewModel.matrixSize = size
        _binding = FragmentMagicSecondBinding.inflate(inflater, container, false)


        binding.konfettiView.start(Presets.rain())

        initGame()

        return binding.root
    }


    private fun initGame() {

        buttons = mutableListOf<Button>()
        binding.container.removeAllViews()
        viewModel.initGame { position ->
            val button = createButton(position.y, position.x, position.id)
            buttons.add(button)
            binding.container.addView(button)
        }
    }

    private fun createButton(x: Float, y: Float, id: Int): Button {

        val button = Button(requireContext())

        button.x = x * viewModel.buttonSize + viewModel.offsetX
        button.y = y * viewModel.buttonSize + viewModel.offsetY
        button.width = viewModel.buttonSize
        button.height = viewModel.buttonSize
        button.id = id

        button.setOnClickListener {
            moveButton(button)
        }

        button.text = id.toString()
        button.background.setTint(Color.CYAN)

        if (button.id == viewModel.matrixSize * viewModel.matrixSize) {
            button.text = " "
            button.tag = "target"
            button.background.setTint(Color.WHITE)
        }

        return button
    }

    private fun moveButton(currentButton: Button) {

        val button = binding.container.findViewWithTag<Button>("target")

        var current = MagicPosition(currentButton.x,currentButton.y)
        var target = MagicPosition(button.x,button.y)

        if (viewModel.move(current,target)) {

            currentButton.animate().xBy(current.x).yBy(current.y).start()

            button.translationX = target.x
            button.translationY = target.y

        }

        if (check()) {
            val snack = Snackbar.make(binding.container,"Congratulations ! You have Completed the magic SQUARE",
                Snackbar.LENGTH_LONG)
            snack.show()
        }
    }

    private fun check(): Boolean {

        val positions = ArrayList<MagicPosition>()

        (1..viewModel.matrixSize * viewModel.matrixSize).forEach {
            val button = binding.container.findViewById<Button>(it)
            positions.add(MagicPosition(button.x,button.y))
        }

        return viewModel.check(positions)
    }

}