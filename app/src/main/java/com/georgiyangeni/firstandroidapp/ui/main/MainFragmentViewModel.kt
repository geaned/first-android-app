package com.georgiyangeni.firstandroidapp.ui.main

import com.georgiyangeni.firstandroidapp.repository.AuthRepositoryOld
import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class MainFragmentViewModel : BaseViewModel() {
    // currently changed between 'true' and 'false' for development purposes
    val isAuthorizedFlow: Flow<Boolean> = AuthRepositoryOld.isAuthorizedFlow
}