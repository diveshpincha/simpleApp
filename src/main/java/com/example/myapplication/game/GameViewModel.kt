package com.example.myapplication.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _moves=MutableLiveData<Int>()
    val moves:LiveData<Int>
        get() = _moves
    private var time: Long? = null
    private val actual = (0..100).random()
    private val _toaster=MutableLiveData<Int>()
    val toaster:LiveData<Int>
    get()= _toaster

    //navigate
    private var _triggerLost = MutableLiveData<Boolean>()
     val triggerLost:LiveData<Boolean>
    get() = _triggerLost

    private var _triggerWon = MutableLiveData<Boolean>()
     val triggerWon:LiveData<Boolean>
        get() = _triggerWon


    init{
        _moves.value=7
        _triggerLost.value=false
        _triggerWon.value=false
    }

    fun getStart(): Long? {
        return time
    }

    fun setStart(startTime: Long) {
        time = startTime
    }

    fun play(guess: Int) {
        // 1= bigger , 2 = smallerr , 3 = win
        if (guess > actual && moves.value!! >0) {
            _toaster.value=1
            _moves.value=_moves.value?.minus(1)
        }
        if (guess < actual && moves.value!! >0) {
            _toaster.value=2
            _moves.value=_moves.value?.minus(1)
        }
        if(guess==actual){
            _toaster.value=3
            _triggerWon.value=true
        }
        if (moves.value!!<=0){
            _triggerLost.value=true
        }

    }
    fun navigationDone(){
        _triggerWon.value=false
        _triggerLost.value=false
    }
}