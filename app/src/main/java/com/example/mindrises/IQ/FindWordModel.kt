package com.example.mindrises.IQ

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mindrises.R
import com.example.mindrises.data.Word
import com.example.mindrises.data.WordDatabase
import com.example.mindrises.data.WordRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class FindWordModel(application:Application):AndroidViewModel(application) {
    private val two_letters= arrayOf(R.array.two_letters)
    private val three_letters= arrayOf(R.array.three_letters)
    private val four_letters= arrayOf(R.array.four_letters)
    private val five_letters= arrayOf(R.array.five_letters)
    private val other_letters= arrayOf(R.array.others)

    private val readAllData: LiveData<List<Word>>
    private val repository: WordRepository
    private var wordToGuess: String ="ABSOLUTE";
    var wordShuffled= MutableLiveData<String>()
    private var length=8;
    private var nb_selected=0;
    var currentWord= MutableLiveData<String>()
    private var index=0
    private var wordSelected=MutableLiveData<String>()
     var words = ArrayList<Word>()
    fun shuffle(s: String): String {
        val helloCharArray = s.toCharArray()
        helloCharArray.shuffle()
        return helloCharArray.concatToString()
    }
    fun createListWords(){
        words.clear()
        words.add(Word(0,two_letters[0].toString(),two_letters[0].toString().length,"en"))
        words.add(Word(0,three_letters[0].toString(),three_letters[0].toString().length,"en"))
        words.add(Word(0,three_letters[1].toString(),three_letters[1].toString().length,"en"))
        words.add(Word(0,three_letters[2].toString(),three_letters[2].toString().length,"en"))
        words.add(Word(0,four_letters[0].toString(),four_letters[0].toString().length,"en"))
        words.add(Word(0,four_letters[1].toString(),four_letters[1].toString().length,"en"))
        words.add(Word(0,five_letters[0].toString(),five_letters[0].toString().length,"en"))
        words.add(Word(0,five_letters[1].toString(),five_letters[1].toString().length,"en"))
        words.add(Word(0,five_letters[2].toString(),five_letters[2].toString().length,"en"))
        words.add(Word(0,other_letters[1].toString(),other_letters[1].toString().length,"en"))

    }
    fun nextWord(){
        wordToGuess=words[index].word
        wordShuffled.value=shuffle(wordToGuess)
        if(index<words.size){
            index++;
        }
        wordSelected.value=words[index].word
    }
    init {
        createListWords()
        //currentWord=words[index].word

        wordSelected.value=""
        val wordDao = WordDatabase.getDatabase(application).getWordDao()
        repository = WordRepository(wordDao)
        readAllData=repository.readAllData

        //Log.i("size is : ",readAllData.value!!.size.toString())
        if(readAllData.value==null || readAllData.value!!.size==0)addWords()
        if(repository==null)
        Log.i("size is : ","repo null")
        else Log.i("size is : ","repo not null")
    }
    fun addWord(word:Word){
        viewModelScope.launch (Dispatchers.IO){
            repository.addWord(word)
        }
    }
    fun addWords(){
        val listwords = arrayListOf<String>(
            "HI","PUT","CUT","JOB","JET","JAR","MOM","COZ","HOT",
            "JAZZ","BUZZ","QUIZ","JUKE","JUMP","JAVA","PINK","DEAL",
            "FUZZY","PIZZA","HELLO","CABIN","DEAL","CABLE","DEALS",
            "EAGLE","FABLE","OCEAN","PUZZLE","ZIGZAG","LETTER","LETTERS",
            "BANANA","BOOM","CABANAS","EAGLES","EARN","ILLEGAL","IMPAIRS",
            "IMMORAL","IMAGINE","IMPLANT","ABSOLUTE","ARGUMENT","FAMILIAR",
            "FESTIVAL","FRIENDLY","ELECTRON","EXCHANGE","MATERIAL",
            "MINIMIZE","RESPONSE","RECEIVED","ULTIMATE"
        )

        listwords.forEach{
            addWord(Word(0,word = it,length=it.length, language = "en"));
        }
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