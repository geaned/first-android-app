package com.georgiyangeni.firstandroidapp.interactor

import com.georgiyangeni.firstandroidapp.entity.User
import com.georgiyangeni.firstandroidapp.repository.UsersRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun loadUsers(): NetworkResponse<List<User>, Unit> =
        usersRepository.getUsers()
}
