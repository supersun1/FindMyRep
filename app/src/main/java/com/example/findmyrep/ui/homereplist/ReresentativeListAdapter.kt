package com.example.findmyrep.ui.homereplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findmyrep.R
import com.example.findmyrep.ui.homerepinfo.CustomViewHolder
import kotlinx.android.synthetic.main.replistview.view.*

class RepresentativeListAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val repTitles = listOf<String>("First title", "Second", "Third")
    val repNames = listOf<String>("First Name", "Second", "Third")
    val repPhotos = listOf<String>("First Photo", "Second", "Third")

    //Number of Representatives shown
    override fun getItemCount(): Int {
        return 25
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.replistview, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val repTitle = repTitles.get(position)
        holder.itemView.repTitleTextView.text = repTitle

        val repName = repNames.get(position)
        holder.itemView.repNameTextView.text = repName

        val repPhoto = repPhotos.get(position)

    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){


    }
