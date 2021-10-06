package com.georgiyangeni.firstandroidapp.ui.signup

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.georgiyangeni.firstandroidapp.ui.base.BaseFragment
import com.georgiyangeni.firstandroidapp.R
import com.georgiyangeni.firstandroidapp.databinding.FragmentSignUpBinding
import com.georgiyangeni.firstandroidapp.ui.signin.SignInViewModel

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    private val viewBinding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignInViewModel by viewModels()
}