package com.example.eletue.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VenueAdapter()
    }

    class VenueAdapter : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {
        val venues = listOf(
                Venue("Trafalor", "Test"),
                Venue("Effeil Tower", "Test mamene"),
                Venue("Tokyo Tower", "Wesh"))

        override fun getItemCount(): Int = venues.size

        override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
            holder.name.text = venues[position].name
            holder.address.text = venues[position].address
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
            val context = parent.context
            val layoutInterface = LayoutInflater.from(context)
            val view = layoutInterface.inflate(R.layout.item_venue, parent, false)
            return VenueViewHolder(view)
        }

        class VenueViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val name = view.findViewById<TextView>(R.id.name)
            val address = view.findViewById<TextView>(R.id.address)
        }
    }
}
