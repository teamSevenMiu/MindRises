package com.example.mindrises

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindWordModel:ViewModel() {
    private var wordToGuess: String ="ABSOLUTE";
    private var length=8;
    private var nb_selected=0;
    private var wordSelected=MutableLiveData<String>()
    init {
        wordSelected.value=""
    }
    fun selectLetter(s:String):MutableLiveData<String>{
        this.wordSelected.value+=s
        return wordSelected
    }
    fun reinit_selected_word():MutableLiveData<String>{
        this.wordSelected.value=""
        return wordSelected
    }
}