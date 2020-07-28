package com.example.findmyrep.ui.dashboardrepinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepresentativeInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is an Representative Info Fragment"
    }
    val text: LiveData<String> = _text
}