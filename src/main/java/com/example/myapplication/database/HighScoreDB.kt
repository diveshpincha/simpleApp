package com.example.myapplication.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HighScores::class],version = 1,exportSchema = false)
abstract class HighScoreDB:RoomDatabase() {
    abstract val highscoresDao:HighscoresDao

    companion object{
        @Volatile
        private var INSTANCE:HighScoreDB?=null

        fun getInstance(context: Context):HighScoreDB{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null){
                    Log.i("timrpass","db was empty")
                    instance=
                        Room.databaseBuilder(context.applicationContext,HighScoreDB::class.java,"hof_db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }


                return instance
            }
        }
    }
}