package com.example.findmyrep.ui.homereplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepresentativeListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is an Representative List Fragment"
    }
    val text: LiveData<String> = _text
}