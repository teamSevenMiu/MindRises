package com.example.mindrises

import android.animation.ObjectAnimator
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.example.mindrises.databinding.ActivityMagicBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MagicActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMagicBinding
    private lateinit var viewModel: MagicViewModel
    private var buttons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MagicViewModel::class.java)
        binding = ActivityMagicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        initGame()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

        val button = Button(this)

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
            val snack = Snackbar.make(binding.container,"Congratulations ! You have Completed the magic SQUARE",Snackbar.LENGTH_LONG)
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

