package com.georgiyangeni.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.net.Uri


// class MainActivity : AppCompatActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onFirstButtonClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.first_button) {
                val uri: Uri =
                    Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }
}