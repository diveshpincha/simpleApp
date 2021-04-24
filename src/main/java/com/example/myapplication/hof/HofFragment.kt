package com.example.myapplication.hof

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.database.HighScoreDB
import com.example.myapplication.databinding.HofFragmentBinding

class HofFragment : Fragment() {


    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : HofFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.hof_fragment,container,false)

        val application= requireNotNull(this.activity).application

        val dataSource = HighScoreDB.getInstance(application).highscoresDao

        val viewModelFactory = HofViewModelFactory(dataSource,application)

        val hofViewModel= ViewModelProvider(this,viewModelFactory).get(HofViewModel::class.java)

        //hofViewModel.display()


        val newString :MutableList<String> = mutableListOf()

        /*hofViewModel.allScores.forEach(){
            newString.add("name : ${it.name} \n Moves Used : ${it.moves} \n Time Taken : ${it.dis_time} \n ")}


        /*WonViewModel.scores.forEach(){
            newString.add("name : ${it.name} \n Moves Used : ${it.moves} \n Time Taken : ${it.dis_time} \n ")
        } */

        val finalString=newString.joinToString("\n")
        binding.textView2.text=finalString*/

        hofViewModel.allScores.observe(viewLifecycleOwner, Observer {
            it.forEach(){
                newString.add("name : ${it.name} \n Moves Used : ${it.moves} \n Time Taken : ${it.dis_time} \n ")}
            val finalString=newString.joinToString("\n")
            binding.textView2.text=finalString
            })

        binding.clear.setOnClickListener{
            Toast.makeText(this.context,"CLEARED", Toast.LENGTH_SHORT)
            it.findNavController().navigate(HofFragmentDirections.actionHofFragmentToTitleFragment())
            hofViewModel.onClear()

        }
        return binding.root


}}