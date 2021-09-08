package com.georgiyangeni.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.net.Uri
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.usersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        adapter.userList = loadUsers()
        adapter.notifyDataSetChanged()  // will come back to this later
    }

    private fun loadUsers(): List<User> {
        return listOf(
            User(
                avatarUrl = "",
                userName = "User name 1",
                groupName = "A"
            ),
            User(
                avatarUrl = "",
                userName = "User name 2",
                groupName = "AB"
            ),
            User(
                avatarUrl = "",
                userName = "User name 3",
                groupName = "CA"
            ),
            User(
                avatarUrl = "",
                userName = "User name 4",
                groupName = "DB"
            ),
            User(
                avatarUrl = "",
                userName = "User name 5",
                groupName = "BA"
            ),
            User(
                avatarUrl = "",
                userName = "User name 6",
                groupName = "C"
            ),
            User(
                avatarUrl = "",
                userName = "User name 7",
                groupName = "D"
            ),
            User(
                avatarUrl = "",
                userName = "User name 8",
                groupName = "AE"
            ),
            User(
                avatarUrl = "",
                userName = "User name 9",
                groupName = "Sausage school"
            )
        )
}

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }

//    fun onFirstButtonClick(view: View?) {
//        if (view != null) {
//            if (view.id == R.id.first_button) {
//                val uri: Uri =
//                    Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
//
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
//            }
//        }
//    }
}