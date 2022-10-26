package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class PuzzleItem(val name:String,val image:Int)

class PuzzleViewModel : ViewModel() {

    var items:MutableLiveData<ArrayList<PuzzleItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<PuzzleItem>()
        list.add(PuzzleItem("Magic Square",R.drawable.magic_square))
        list.add(PuzzleItem("Jigsaw",R.drawable.jigsaw))
        items.value = list
    }
}