package com.example.myapplication.game

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.HighScoreDB
import com.example.myapplication.database.HighScores
import com.example.myapplication.database.WonViewModel
import com.example.myapplication.database.WonViewModelFactory
import com.example.myapplication.databinding.FragmentWonBinding

class WonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentWonBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_won,container,false)
        // Inflate the layout for this fragmen

        val args= arguments?.let { WonFragmentArgs.fromBundle(it) }

        binding.moves.text=" IN ${args?.movesUsed!!} MOVES \n AND"

        binding.timerr.base = SystemClock.elapsedRealtime()-args?.timeTaken!!

        binding.button.setOnClickListener{
            view:View->view.findNavController().navigate(WonFragmentDirections.actionWonFragmentToGameFragment())
        }

        val application= requireNotNull(this.activity).application

        val dataSource = HighScoreDB.getInstance(application).highscoresDao

        val viewModelFactory = WonViewModelFactory(dataSource,application)

        val wonViewModel= ViewModelProvider(this,viewModelFactory).get(WonViewModel::class.java)

        //binding.setLifecycleOwner(this)
        val score=HighScores()

        score.dis_time=binding.timerr.text.toString()
        score.time=args?.timeTaken!!
        score.moves=args?.movesUsed!!


        binding.addToDb.setOnClickListener{
            score.name= binding.editTextTextPersonName.text.toString()
            wonViewModel.onSubmit(score)
            binding.addToDb.setVisibility(View.GONE)
            binding.editTextTextPersonName.setVisibility(View.GONE)
            Toast.makeText(this.context,"ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show()
        }

        return  binding.root
    }
}