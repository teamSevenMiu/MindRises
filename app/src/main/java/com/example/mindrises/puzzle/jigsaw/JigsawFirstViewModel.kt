package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class JigsawItem(val name:String,val image:Int,val piece:Int)

class JigsawFirstViewModel : ViewModel() {

    var items: MutableLiveData<ArrayList<JigsawItem>>

    init {
        items = MutableLiveData()
        val list = ArrayList<JigsawItem>()
        list.add(JigsawItem("jigsaw1.jpg",R.drawable.jigsaw1,4))
        list.add(JigsawItem("jigsaw2.jpg",R.drawable.jigsaw2,9))
        list.add(JigsawItem("jigsaw3.jpg",R.drawable.jigsaw3,16))
        list.add(JigsawItem("jigsaw4.jpg",R.drawable.jigsaw4,4))
        list.add(JigsawItem("jigsaw5.jpg",R.drawable.jigsaw5,9))
        list.add(JigsawItem("jigsaw6.jpg",R.drawable.jigsaw6,16))
        list.add(JigsawItem("jigsaw7.jpg",R.drawable.jigsaw7,16))
        list.add(JigsawItem("jigsaw8.jpg",R.drawable.jigsaw8,16))
        items.value = list
    }
}