package com.georgiyangeni.firstandroidapp.ui.main

import com.georgiyangeni.firstandroidapp.repository.AuthRepository
import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : BaseViewModel() {
    // currently changed between 'true' and 'false' for development purposes
    val isAuthorizedFlow: Flow<Boolean> = AuthRepository.isAuthorizedFlow
}