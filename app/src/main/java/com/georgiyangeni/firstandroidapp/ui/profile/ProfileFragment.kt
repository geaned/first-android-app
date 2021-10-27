package com.georgiyangeni.firstandroidapp.ui.profile

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentProfileBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile){

    private val viewBinding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()
}