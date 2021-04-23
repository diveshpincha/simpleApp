package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighscoresDao {
    @Insert
    fun insert(scores: HighScores)

    @Query("DELETE FROM hof_table")
    fun clear()

    @Query("select * from hof_table order by milli_time desc limit 10")
    fun display(): LiveData<List<HighScores>>
}