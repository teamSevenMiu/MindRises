package com.example.mindrises.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun addWord(word:Word)
    @Query("SELECT * FROM word_table")
    fun readAllData():LiveData<List<Word>>
    @Query("SELECT * FROM word_table WHERE length= :length")
    fun selectLength(length:Int):LiveData<List<Word>>

}