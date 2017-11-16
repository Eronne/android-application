package com.example.eletue.application

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class VenueAdapter(private val venues: List<Venue>, private val venueCallback: (String) -> Unit) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {
    override fun getItemCount(): Int = venues.size

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val (id, name, location) = venues[position]

        holder.name.text = name
        holder.formattedAddress.text = location.formattedAddress[0]

        holder.itemView.setOnClickListener {
            venueCallback.invoke(id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val context = parent.context
        val layoutInterface = LayoutInflater.from(context)
        val view = layoutInterface.inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(view)
    }

    class VenueViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val formattedAddress = view.findViewById<TextView>(R.id.address)
    }
}