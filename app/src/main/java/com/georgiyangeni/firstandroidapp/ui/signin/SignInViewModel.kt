package com.georgiyangeni.firstandroidapp.ui.signin

import androidx.lifecycle.viewModelScope
import com.georgiyangeni.firstandroidapp.repository.AuthRepository
import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {

    fun signIn() {
        viewModelScope.launch {
            AuthRepository.signIn()
        }
    }
}