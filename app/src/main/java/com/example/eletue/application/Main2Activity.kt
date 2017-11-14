package com.example.eletue.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val percent = intent.getIntExtra("percent", 0)
        findViewById<TextView>(R.id.percentValue).apply {
            this.text = "Percent: $percent"
        }
    }
}
