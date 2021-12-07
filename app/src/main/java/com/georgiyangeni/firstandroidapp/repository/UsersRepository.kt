package com.georgiyangeni.firstandroidapp.repository

import com.georgiyangeni.firstandroidapp.data.network.Api
import com.georgiyangeni.firstandroidapp.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getUsers(): NetworkResponse<List<User>, Unit> =
        api.getUsers()
}
