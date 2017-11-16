package com.example.eletue.application

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val venueURL = "https://api.foursquare.com/v2/venues/search?ll=44.3,37.2&near=Chicago, IL&client_id=HOL02FOPS33OHIIZDGKYEVIJNIJOGSOG2BTWE40B4C1XBKDR&client_secret=H04HFNQTWSVQKQ2D03FWT2TP0NFM24I1D12HMBAYZLX2OJ5B&v=20171115"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val client = OkHttpClient()
        val request = Request.Builder().url(venueURL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Toast.makeText(this@MainActivity, e?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                json?.run {
                    runOnUiThread {
                        handleJson(this, recyclerView)
                    }
                }
            }

        })
    }

    private fun handleJson(json: String?, recyclerView: RecyclerView) {
        if (!json.isNullOrEmpty()) {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(VenueResponseRoot::class.java)
            val venueResponse = adapter.fromJson(json!!)

            if (venueResponse != null) {
                val venues = venueResponse.response.venues
                recyclerView.adapter = VenueAdapter(venues, { id ->
                    val i = Intent(this, VenueDetailsActivity::class.java)
                    i.putExtra("id", id)
                    startActivity(i)
                })
            }
        }
    }

    class VenueAdapter(private val venues: List<Venue>, private val venueCallback: (String) -> Unit) : RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {
        override fun getItemCount(): Int = venues.size

        override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
            val ctx = holder.itemView.context

            val (id, name, location) = venues[position]

            holder.name.text = name
            holder.address.text = location.formattedAddress[0]

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
            val address = view.findViewById<TextView>(R.id.address)
        }
    }
}
