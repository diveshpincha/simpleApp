package com.example.myapplication.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.*

class WonViewModel (val database: HighscoresDao,application: Application): AndroidViewModel(Application()) {
    private var viewModelJob = Job()

    //var allScores= listOf(HighScores())


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



    private val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)

    fun onSubmit(score: HighScores) {
        uiScope.launch {
            insert(score)
        }
    }

    private suspend  fun insert(score:HighScores){
        withContext(Dispatchers.IO){
            database.insert(score)
            Log.i("timepass","insert happened")
        }
    }

   /* private suspend fun display() {
        withContext(Dispatchers.IO){
            allScores = database.display()
        }
    }*/

    /*fun onClear(){
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }*/

}