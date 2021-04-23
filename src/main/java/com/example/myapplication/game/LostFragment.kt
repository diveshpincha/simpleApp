package com.example.myapplication.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLostBinding

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentLostBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lost,container,false)
        binding.button2.setOnClickListener{
            it.findNavController().navigate(BlankFragmentDirections.actionLostFragmentToGameFragment())
        }
        return binding.root
    }
}