package com.example.mindrises.IQ

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mindrises.PuzzleItem
import com.example.mindrises.R

class IQViewModel : ViewModel() {
    var items: MutableLiveData<ArrayList<PuzzleItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<PuzzleItem>()
        list.add(PuzzleItem("Find Next Number", R.drawable.number_blocks))
        list.add(PuzzleItem("Find the Word", R.drawable.puzzle))
        list.add(PuzzleItem("Memorize_pattern", R.drawable.memorize))
        items.value = list
    }
}