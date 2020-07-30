package com.example.findmyrep.ui.elections_calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.findmyrep.R
import androidx.databinding.DataBindingUtil
import com.example.findmyrep.databinding.FragmentElectionCalendarBinding

class ElectionCalendarFragment : Fragment() {

    companion object{
        fun newInstance() = ElectionCalendarFragment()
    }
    private lateinit var viewModel: ElectionCalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentElectionCalendarBinding>(inflater, R.layout.fragment_election_calendar, container, false)
        viewModel = ViewModelProviders.of(this).get(ElectionCalendarViewModel::class.java)

        viewModel._response.observe(viewLifecycleOwner, Observer { newresponse ->
            binding.helloWorld.text = newresponse.toString() //display the raw json data
        })
        return binding.root
        //return inflater.inflate(R.layout.fragment_election_calendar, container, false
    }






//
//        electionCalendarViewModel =
//            ViewModelProviders.of(this).get(ElectionCalendarViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_election_calendar, container, false)
//        val textView: TextView = root.findViewById(R.id.electionCalendarFragment)
//        electionCalendarViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//
//        return root


    }


    
