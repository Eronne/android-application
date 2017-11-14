package com.example.eletue.application

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
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
            val i = Intent(this.applicationContext, Main2Activity::class.java)
            startActivity(i)
        }

        val counter : TextView = findViewById(R.id.textView)

        val seekBar : SeekBar = findViewById(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {
                counter.text = p1.toString() + " %"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}
