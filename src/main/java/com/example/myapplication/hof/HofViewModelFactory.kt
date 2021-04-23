package com.example.myapplication.hof

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.HighscoresDao

class HofViewModelFactory (private val dataSource : HighscoresDao ,private val application: Application): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if ( modelClass.isAssignableFrom(HofViewModel::class.java)){
            return HofViewModel(dataSource,application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}