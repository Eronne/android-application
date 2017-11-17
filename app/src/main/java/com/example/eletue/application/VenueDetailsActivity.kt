package com.example.eletue.application

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

class VenueDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue_details)

        val id = intent.getStringExtra("id")
        val detailsURL = "https://api.foursquare.com/v2/venues/" + id + "?client_id=HOL02FOPS33OHIIZDGKYEVIJNIJOGSOG2BTWE40B4C1XBKDR&client_secret=H04HFNQTWSVQKQ2D03FWT2TP0NFM24I1D12HMBAYZLX2OJ5B&v=20171115"

        val client = OkHttpClient()
        val request = Request.Builder().url(detailsURL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Toast.makeText(this@VenueDetailsActivity, e?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                json?.run {
                if (!json.isNullOrEmpty()) {
                    val moshi = Moshi.Builder().build()
                    val adapter = moshi.adapter(VenueResponseRoot::class.java)
                    val venueDetailsResponse = adapter.fromJson(json!!)

                    if (venueDetailsResponse != null) {
                        runOnUiThread {
                            val venue = venueDetailsResponse.response.venue
                            val name = venue.name
                            val location = venue.location

                            if (venue.photos.count > 0) {
                                val img = findViewById<ImageView>(R.id.image)
                                img.visibility = View.VISIBLE
                                val imgUrl = venue.photos.groups[0].items[0].getImgUrl(300, 500)
                                Picasso.with(this@VenueDetailsActivity).load(imgUrl).into(img)
                            }

                            val venueName = findViewById<TextView>(R.id.name)
                            val formattedAddress = findViewById<TextView>(R.id.formattedAddress)
                            venueName.text = name
                            formattedAddress.text = location.formattedAddress[0]
                        }
                    }
                }
                }
            }
        })
    }
}
