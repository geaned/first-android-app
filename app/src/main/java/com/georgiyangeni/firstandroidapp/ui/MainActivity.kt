package com.georgiyangeni.firstandroidapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.georgiyangeni.firstandroidapp.R

class MainActivity : AppCompatActivity() {

    companion object {
        val LOG_TAG = "MyLogTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate()")
        setContentView(R.layout.activity_main)
    }
}
