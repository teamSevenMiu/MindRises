package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class JigsawItem(val name:String,val image:Int)

class JigsawFirstViewModel : ViewModel() {

    var items: MutableLiveData<ArrayList<JigsawItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<JigsawItem>()
        list.add(JigsawItem("jigsaw1.jpg",R.drawable.jigsaw1))
        list.add(JigsawItem("jigsaw2.jpg",R.drawable.jigsaw2))
        list.add(JigsawItem("jigsaw3.jpg",R.drawable.jigsaw3))
        list.add(JigsawItem("jigsaw4.jpg",R.drawable.jigsaw4))
        list.add(JigsawItem("jigsaw5.jpg",R.drawable.jigsaw5))
        list.add(JigsawItem("jigsaw6.jpg",R.drawable.jigsaw6))
        list.add(JigsawItem("jigsaw7.jpg",R.drawable.jigsaw7))
        list.add(JigsawItem("jigsaw8.jpg",R.drawable.jigsaw8))
        items.value = list
    }
}