package com.example.myapplication.game

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.GameFragmentBinding


class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : GameFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.game_fragment,container,false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameModel=viewModel

        binding.setLifecycleOwner(this)

        //TIMER
        val chronometer: Chronometer=binding.timeUsed
        if(viewModel.getStart()==null){
            var startTime : Long= SystemClock.elapsedRealtime()
            viewModel.setStart(startTime)
            chronometer.setBase(startTime)
        }
        else{
            chronometer.setBase(viewModel.getStart()!!)
        }
        chronometer.start()

        binding.guess.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                viewModel.play(Integer.parseInt(binding.guess.text.toString()))
                binding.guess.setText("")
                return@OnKeyListener true
            }
            false
        })

        //played move
        viewModel.toaster.observe(viewLifecycleOwner, Observer {
            when (it){
                1->Toast.makeText(this.context,"YOUR GUESS WAS HIGHER",Toast.LENGTH_SHORT).show()
                    //1->binding.movesLeft.text="thari hau"
                2->Toast.makeText(this.context,"YOUR GUESS WAS LOWER",Toast.LENGTH_SHORT).show()
                else->Toast.makeText(this.context,"CONGRATULATIONS ! YOU GUESSED IT",Toast.LENGTH_SHORT).show()
            }
        })

        //nav to won frag
        viewModel.triggerWon.observe(viewLifecycleOwner, Observer {
            if(it){
                chronometer.stop()
                val action=GameFragmentDirections.actionGameFragmentToWonFragment()
                action.timeTaken = SystemClock.elapsedRealtime()-chronometer.getBase()
                action.movesUsed = 7-viewModel.moves.value!!
                binding.guess.findNavController().navigate(action)

                viewModel.navigationDone()
            }
        })

        viewModel.triggerLost.observe(viewLifecycleOwner, Observer {
            if(it){
                val action=GameFragmentDirections.actionGameFragmentToLostFragment()
                binding.movesLeft.findNavController().navigate(action)
            }
        })

        return  binding.root
    }

}