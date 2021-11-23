package com.georgiyangeni.firstandroidapp.ui

import com.georgiyangeni.firstandroidapp.ui.base.BaseViewModel
import com.georgiyangeni.firstandroidapp.interactor.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {

    suspend fun isAuthorizedFlow(): Flow<Boolean> = authInteractor.isAuthorized()
}