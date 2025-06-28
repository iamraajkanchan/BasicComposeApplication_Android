package com.chinky.family.data.repository

import com.chinky.family.data.db.UserDao
import com.chinky.family.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    suspend fun getUsers(): List<User> {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val users = response.body() ?: emptyList()
                userDao.insertUsers(users)
                users
            } else {
                userDao.getAllUsers()
            }
        } catch (e: Exception) {
            userDao.getAllUsers()
        }
    }

    suspend fun getUserById(id: Int): User? {
        return try {
            val response = apiService.getUserById(id)
            if (response.isSuccessful) {
                val user = response.body()
                user?.let {
                    userDao.insertUser(it)
                }
                user
            } else {
                userDao.getUserById(id)
            }
        } catch (e: Exception) {
            userDao.getUserById(id)
        }
    }

    suspend fun createUser(user: User): User? {
        return try {
            val response = apiService.createUser(user)
            if (response.isSuccessful) {
                val createdUser = response.body()
                createdUser?.let {
                    userDao.insertUser(it)
                }
                createdUser
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun updateUser(user: User): User? {
        return try {
            val response = apiService.updateUser(user.id, user)
            if (response.isSuccessful) {
                userDao.updateUser(user)
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun deleteUser(id: Int): Boolean {
        return try {
            val response = apiService.deleteUser(id)
            if (response.isSuccessful) {
                userDao.deleteUser(id)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    fun getUsersFromLocalDatabase(): Flow<List<User>> =
        flow {
            emit(userDao.getAllUsers())
        }

}