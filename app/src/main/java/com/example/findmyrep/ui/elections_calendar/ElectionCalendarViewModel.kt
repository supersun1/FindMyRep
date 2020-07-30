package com.example.findmyrep.ui.elections_calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.DataBindingUtil
import com.example.findmyrep.Election
import com.example.findmyrep.Network.ElectionsApi
import com.example.findmyrep.electionsX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ElectionsAPPID = "AIzaSyANpZIdTe_F3CzRpxBgxztTpQ_4YKVJJxs"
class ElectionCalendarViewModel : ViewModel() {

     val _response = MutableLiveData<String>()

    init {
        getElectionsProperties()
    }


    private fun getElectionsProperties() {
        ElectionsApi.retrofitService.getProperties(ElectionsAPPID).enqueue(
            object : Callback<electionsX> {
                override fun onFailure(call: Call<electionsX>, t: Throwable) {
                    _response.value = "Fails" + t.message
                }

                override fun onResponse(call: Call<electionsX>, response: Response<electionsX>) {
                    //_response.value = response.body().toString()
                    _response.value= "Success: ${response.body()?.elections} Election retrived"
                }

            }
        )

    }
}