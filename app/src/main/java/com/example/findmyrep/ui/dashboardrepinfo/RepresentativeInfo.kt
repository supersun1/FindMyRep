package com.example.findmyrep.ui.dashboardrepinfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmyrep.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.representative_info_fragment.*


class RepresentativeInfo : Fragment() {

    companion object {
        fun newInstance() = RepresentativeInfo()
    }

    private lateinit var viewModel: RepresentativeInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.representative_info_fragment, container, false)

        recyclerViewRepInfo.layoutManager = LinearLayoutManager(activity)
        recyclerViewRepInfo.adapter = RepresentativeInfoAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RepresentativeInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}