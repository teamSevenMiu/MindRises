package com.example.mindrises.IQ

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindrises.PuzzleItem
import com.example.mindrises.R

class WordViewModel : ViewModel() {

    //var items: MutableLiveData<ArrayList<String>>

    init {
        //items = MutableLiveData()
        val list = ArrayList<String>()
        list.add("A")
        list.add("B")
        list.add("C")
        list.add("D")
        //items.value = list
    }
}