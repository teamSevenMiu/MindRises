package com.example.mindrises

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MathFragment : Fragment() {

    companion object {
        fun newInstance() = MathFragment()
    }

    private lateinit var viewModel: MathViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MathViewModel::class.java)
        return inflater.inflate(R.layout.fragment_math, container, false)
    }

}