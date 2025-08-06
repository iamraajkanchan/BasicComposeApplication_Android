package com.chinky.family.domain.usecase

import com.chinky.family.data.repository.UserRepository
import com.chinky.family.domain.model.User
import javax.inject.Inject

class HandleUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend fun getUsers() = repository.getUsers()
    suspend fun deleteUser(id: Int) = repository.deleteUser(id)
    suspend fun createUser(user: User) = repository.createUser(user)
    suspend fun updateUser(user: User) = repository.updateUser(user)

}