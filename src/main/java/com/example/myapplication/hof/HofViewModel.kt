package com.example.myapplication.hof

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.database.HighscoresDao
import kotlinx.coroutines.*

class HofViewModel (val database: HighscoresDao,application: Application): AndroidViewModel(Application()) {
    private var viewModelJob = Job()

    var allScores = database.display()



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



    private val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)


   /* fun display(){
        uiScope.launch {
            _display()
        }
    }

    private suspend fun _display() {
         withContext(Dispatchers.IO){
            allScores = database.display() //as MutableList<HighScores>
        }
    }*/

    fun onClear(){
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

}