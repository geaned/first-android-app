package com.georgiyangeni.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// class MainActivity : AppCompatActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}