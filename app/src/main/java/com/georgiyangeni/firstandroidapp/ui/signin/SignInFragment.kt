package com.georgiyangeni.firstandroidapp.ui.signin

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignInViewModel by viewModels()
}