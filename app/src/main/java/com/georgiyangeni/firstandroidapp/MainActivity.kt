package com.georgiyangeni.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = setupRecyclerView()

        findViewById<View>(R.id.usersRecyclerView).isVisible = false
        findViewById<View>(R.id.progressBar).isVisible = true

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.userList = loadUsers()
                adapter.notifyDataSetChanged()  // will come back to this later
                findViewById<View>(R.id.usersRecyclerView).isVisible = true
                findViewById<View>(R.id.progressBar).isVisible = false
            }
        }
    }

    private fun setupRecyclerView(): UserAdapter {
        val recyclerView = findViewById<RecyclerView>(R.id.usersRecyclerView)
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        return adapter
    }

    private suspend fun loadUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            //Thread.sleep(3000)  // for progress bar testing
            provideApi().getUsers().data
        }
    }

    private fun provideApi(): Api {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(Api::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

//    private fun loadUsers(): List<User> {
//        return listOf(
//            User(
//                avatarUrl = "",
//                userName = "User name 1",
//                groupName = "A"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 2",
//                groupName = "AB"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 3",
//                groupName = "CA"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 4",
//                groupName = "DB"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 5",
//                groupName = "BA"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 6",
//                groupName = "C"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 7",
//                groupName = "D"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 8",
//                groupName = "AE"
//            ),
//            User(
//                avatarUrl = "",
//                userName = "User name 9",
//                groupName = "Sausage school"
//            )
//        )
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
//}