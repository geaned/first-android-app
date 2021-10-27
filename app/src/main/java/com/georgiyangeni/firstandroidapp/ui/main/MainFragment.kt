package com.georgiyangeni.firstandroidapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            val navController =
                (childFragmentManager.findFragmentById(R.id.mainFragmentNavigationHost) as NavHostFragment).navController
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}