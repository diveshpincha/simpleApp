package com.example.myapplication.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:TitleFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.title_fragment,container,false)

        binding.playButton.setOnClickListener{
            view:View -> view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        binding.howTo.setOnClickListener{
                view:View -> view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToHowToFragment())
        }

        binding.HOF.setOnClickListener{
            view:View->view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToHofFragment())
        }


        return binding.root
    }

}