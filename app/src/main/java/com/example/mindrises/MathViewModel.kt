package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MathItem(val name:String,val image:Int)

class MathViewModel : ViewModel() {

    var items: MutableLiveData<ArrayList<PuzzleItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<PuzzleItem>()
        list.add(PuzzleItem("Simple Question",R.drawable.simplequestions))
        list.add(PuzzleItem("True & False",R.drawable.trueandfalse))
        list.add(PuzzleItem("Write your answer",R.drawable.inputmath))
        items.value = list
    }
}