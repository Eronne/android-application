package com.example.eletue.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class VenueDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue_details)
        Log.i("INFO", intent.getStringExtra("id"))
    }
}
