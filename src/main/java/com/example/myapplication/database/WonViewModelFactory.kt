package com.example.myapplication.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WonViewModelFactory(private val dataSource :HighscoresDao, private val application: Application): ViewModelProvider.Factory{

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if ( modelClass.isAssignableFrom(WonViewModel::class.java)){
            return WonViewModel(dataSource,application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}