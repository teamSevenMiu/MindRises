package com.example.mindrises

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class IQFragment : Fragment() {

    companion object {
        fun newInstance() = IQFragment()
    }

    private lateinit var viewModel: IQViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(IQViewModel::class.java)
        return inflater.inflate(R.layout.fragment_iq, container, false)
    }


}