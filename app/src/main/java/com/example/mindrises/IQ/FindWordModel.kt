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
    private val two_letters= application.getResources().getStringArray(R.array.two_letters)
    private val three_letters= application.getResources().getStringArray(R.array.three_letters)
    private val four_letters= application.getResources().getStringArray(R.array.four_letters)
    private val five_letters= application.getResources().getStringArray(R.array.five_letters)
    private val other_letters= application.getResources().getStringArray(R.array.others)
    var nbCorrect=MutableLiveData<Int>()
    var nbWrong=MutableLiveData<Int>()
    //private val readAllData: LiveData<List<Word>>
    //private val repository: WordRepository
    //private var wordToGuess: String ="ABSOLUTE";
    //var wordShuffled= MutableLiveData<String>()
    //private var length=8;
    //private var nb_selected=0;
    var currentWord= MutableLiveData<String>()
    private var index=0
    var wordSelected=MutableLiveData<String>()
    var words = ArrayList<Word>()
    fun shuffle(s: String): String {
        val helloCharArray = s.toCharArray()
        helloCharArray.shuffle()
        return helloCharArray.concatToString()
    }
    fun endSelection()=wordSelected.value!!.length==currentWord.value!!.length
    fun endGame()=index==words.size
    fun correctGuess()=currentWord.value.equals(wordSelected.value)
    fun createListWords(){
        words.clear()
        words.add(Word(0,two_letters[0].toString(),two_letters[0].toString().length,"en"))
        words.add(Word(0,three_letters[0].toString(),three_letters[0].toString().length,"en"))
        words.add(Word(0,three_letters.get(1).toString(),three_letters.get(1).toString().length,"en"))
        words.add(Word(0,three_letters[2].toString(),three_letters[2].toString().length,"en"))
        words.add(Word(0,four_letters[0].toString(),four_letters[0].toString().length,"en"))
        words.add(Word(0,four_letters[1].toString(),four_letters[1].toString().length,"en"))
        words.add(Word(0,five_letters[0].toString(),five_letters[0].toString().length,"en"))
        words.add(Word(0,five_letters[1].toString(),five_letters[1].toString().length,"en"))
        words.add(Word(0,five_letters[2].toString(),five_letters[2].toString().length,"en"))
        words.add(Word(0,other_letters[1].toString(),other_letters[1].toString().length,"en"))

    }
    fun upCorrect(){nbCorrect.value=nbCorrect.value!!+1}
    fun upWrong(){nbWrong.value=nbWrong.value!!+1}
    fun nextWord(){
        currentWord.value=words[index].word

        if(index<words.size){
            index++;
        }
        wordSelected.value=""

    }
    fun reInit(){
        nbCorrect.value=0
        nbWrong.value=0
        index=0
        createListWords()
        nextWord()
        wordSelected.value=""
    }
    init {
        nbCorrect.value=0
        nbWrong.value=0
        createListWords()
        nextWord()
        wordSelected.value=""
        //val wordDao = WordDatabase.getDatabase(application).getWordDao()
        //repository = WordRepository(wordDao)
        //readAllData=repository.readAllData

        //Log.i("size is : ",readAllData.value!!.size.toString())
        //if(readAllData.value==null || readAllData.value!!.size==0)addWords()
        //if(repository==null)

    }
    fun addWord(word:Word){
        viewModelScope.launch (Dispatchers.IO){
            //repository.addWord(word)
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