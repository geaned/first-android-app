package com.georgiyangeni.firstandroidapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.ActivityMainBinding
import com.georgiyangeni.firstandroidapp.ui.main.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()

    companion object {
        val LOG_TAG = "MyLogTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        subscribeToAuthorizationStatus()
        Timber.d("onCreate()")
    }

    private fun subscribeToAuthorizationStatus() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isAuthorizedFlow().collect {
                    showSuitableNavigationFlow(it)
                }
            }
        }
    }

    private fun showSuitableNavigationFlow(isAuthorized: Boolean) {
        val navController = findNavController(R.id.mainActivityNavigationHost)
        when (isAuthorized) {
            true -> {
                if (navController.backQueue.any { it.destination.id == R.id.registered_user_nav_graph }) {
                    return
                }
                navController.navigate(R.id.action_RegisteredUserNavGraph)
            }
            false -> {
                if (navController.backQueue.any { it.destination.id == R.id.guest_nav_graph }) {
                    return
                }
                navController.navigate(R.id.action_GuestNavGraph)
            }
        }
    }
}
