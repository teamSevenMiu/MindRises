package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class MagicItem(val name:String,val image:Int,val size:Int)

class MagicFirstViewModel : ViewModel() {

    var items: MutableLiveData<ArrayList<MagicItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<MagicItem>()
        list.add(MagicItem("2*2",R.drawable.two,2))
        list.add(MagicItem("3*3",R.drawable.three,3))
        list.add(MagicItem("4*4",R.drawable.four,4))
        items.value = list
    }
}