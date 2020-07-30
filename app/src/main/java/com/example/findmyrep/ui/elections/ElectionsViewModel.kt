package com.example.findmyrep.ui.elections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.fragment_election_calendar.*

class ElectionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Find Upcoming Elections"
    }

    val text: LiveData<String> = _text

}