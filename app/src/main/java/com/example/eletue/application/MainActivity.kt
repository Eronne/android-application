package com.example.eletue.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helloText : TextView = findViewById(R.id.hello)
        val clickButton : Button = findViewById(R.id.button)
        clickButton.setOnClickListener {
            Toast.makeText(this, "You just clicked me!", Toast.LENGTH_SHORT).show()
        }
    }
}
