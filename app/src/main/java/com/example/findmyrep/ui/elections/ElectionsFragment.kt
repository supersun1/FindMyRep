package com.example.findmyrep.ui.elections

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.findmyrep.R
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.findmyrep.MainActivity
import com.example.findmyrep.ui.elections_calendar.ElectionCalendarFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_election_calendar.*
import kotlinx.android.synthetic.main.fragment_elections.*
//import kotlinx.android.synthetic.main.fragment_election_calendar.testCard

class ElectionsFragment : Fragment() {

    private lateinit var electionsViewModel: ElectionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        electionsViewModel =
            ViewModelProviders.of(this).get(ElectionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_elections, container, false)
        val textView: TextView = root.findViewById(R.id.text_elections)
        electionsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.id_check_calendar).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_elections_to_electionCalendarFragment)
        }
        view.findViewById<CardView>(R.id.id_vote_mail).setOnClickListener {
            findNavController().navigate(R.id.action_navigation_elections_to_voteMailFragment)
        }
    }

}









