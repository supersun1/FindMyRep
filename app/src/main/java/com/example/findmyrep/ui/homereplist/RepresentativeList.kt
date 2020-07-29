package com.example.findmyrep.ui.homereplist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmyrep.R
import kotlinx.android.synthetic.main.representative_list_fragment.*


class RepresentativeList : Fragment() {

    companion object {
        fun newInstance() = RepresentativeList()
    }

    private lateinit var viewModel: RepresentativeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.representative_list_fragment, container, false)


        recyclerViewRepList.layoutManager = LinearLayoutManager(activity)
        recyclerViewRepList.adapter = RepresentativeListAdapter()

        fetchRepData()
    }

    fun fetchRepData() {

        println("Attepmting to Fetch JSON")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepresentativeListViewModel::class.java)
    }

}
