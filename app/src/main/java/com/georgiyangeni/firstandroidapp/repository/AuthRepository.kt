package com.georgiyangeni.firstandroidapp.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {
    // currently changed between 'true' and 'false' for development purposes
    private val _isAuthorizedFlow = MutableStateFlow(false)
    val isAuthorizedFlow = _isAuthorizedFlow.asStateFlow()
    // one-directional data flow, should not be able to write while reading

    suspend fun signIn(email: String, password: String) {
        // TODO: http request to backend for auth
        _isAuthorizedFlow.emit(true)
    }

    suspend fun signUp(
        firstname: String,
        lastname: String,
        nickname: String,
        email: String,
        password: String
    ) {
        // TODO: http request to backend to check availability and go to email confirmation
    }

    suspend fun logOut() {
        _isAuthorizedFlow.emit(false)
    }
}