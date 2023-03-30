package com.example.smarthome

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.smarthome.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vText = findViewById<TextView>(id.push_me)
        vText.setOnClickListener{
            Log.e("tag", "test")
            val i= Intent(this, SecondActivity::class.java)
            startActivity(i)
        }
    }

}