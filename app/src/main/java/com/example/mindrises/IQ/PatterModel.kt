package com.example.mindrises.IQ

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PatterModel: ViewModel() {
    var nbCorrect=MutableLiveData<Int>()
    var nbWrong=MutableLiveData<Int>()

    lateinit var pattern:MutableLiveData<List<Int>>
    var index:Int = 0
    var patterns=ArrayList<List<Int>>()
    fun addCorrect(){
        nbCorrect.value=nbCorrect.value!! + 1
    }
    fun addWrong(){
        nbWrong.value=nbWrong.value!! + 1
    }
    fun reinit(){
        index=0
        generate_patterns()
        nbCorrect.value=0
        nbWrong.value=0
        nextPatter()
    }
    fun generate_patterns(){
        for(i in 0..9){
            patterns.add(ArrayList<Int>())
            if(i<2){
                val v=2
                patterns[i]=(0..8).shuffled().take(v)
            }else if (i<5){
                val v=3
                patterns[i]=(0..8).shuffled().take(v)
            }
            else if (i<7){
                val v=4
                patterns[i]=(0..8).shuffled().take(v)
            }
            else {
                val v=5
                patterns[i]=(0..8).shuffled().take(v)
            }
        }
    }
    fun endPatters():Boolean{
        return index>=patterns.size
    }
    fun nextPatter(){
        pattern.value=patterns[index]
        if(index<patterns.size)
            index++
    }
    init {
        pattern=MutableLiveData<List<Int>>()
        generate_patterns()
        nbCorrect.value=0
        nbWrong.value=0
        nextPatter()

    }
}