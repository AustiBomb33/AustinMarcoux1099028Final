package com.marcoux.austinmarcoux1099028final

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val context: Context, private val destinations: List<Destination>):
    RecyclerView.Adapter<RecyclerViewAdapter.DestinationViewHolder>() {
    inner class DestinationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val destRoot = itemView.findViewById<ConstraintLayout>(R.id.destRoot)!!
        val nameText = itemView.findViewById<TextView>(R.id.textName)!!
        val rankText = itemView.findViewById<TextView>(R.id.textRank)!!
        val descText = itemView.findViewById<TextView>(R.id.textDesc)!!
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.destination_layout, parent, false)
        return DestinationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        with(holder){
            nameText.text = destination.name
            descText.text = destination.desc
            rankText.text = destination.rank.toString()
        }
    }

}
