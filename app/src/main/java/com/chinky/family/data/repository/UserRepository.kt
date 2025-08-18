package com.chinky.family.data.repository

import android.net.ConnectivityManager
import com.chinky.family.data.db.AppDao
import com.chinky.family.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao,
    private val connectivityManager: ConnectivityManager
) {

    fun isNetworkAvailable(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    suspend fun getUsers(): List<User> {
        return try {
            val response = apiService.getUsers()
            if (isNetworkAvailable()) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    appDao.insertUsers(users)
                    users
                } else {
                    appDao.getAllUsers()
                }
            } else {
                appDao.getAllUsers()
            }
        } catch (e: Exception) {
            appDao.getAllUsers()
        }
    }

    suspend fun getUserById(id: Int): User? {
        return try {
            val response = apiService.getUserById(id)
            if (response.isSuccessful) {
                val user = response.body()
                user?.let {
                    appDao.insertUser(it)
                }
                user
            } else {
                appDao.getUserById(id)
            }
        } catch (e: Exception) {
            appDao.getUserById(id)
        }
    }

    suspend fun createUser(user: User): User? {
        return try {
            val response = apiService.createUser(user)
            if (response.isSuccessful) {
                val createdUser = response.body()
                createdUser?.let {
                    appDao.insertUser(it)
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
                appDao.updateUser(user)
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            return null
        }
    }

    suspend fun deleteUser(id: Int): Flow<List<User>> {
        return try {
            val response = apiService.deleteUser(id)
            if (response.isSuccessful) {
                appDao.deleteUser(id)
                flow {
                    emit(appDao.getAllUsers())
                }
            } else {
                flow {
                    emit(emptyList<User>())
                }
            }
        } catch (e: Exception) {
            flow {
                emit(emptyList<User>())
            }
        }
    }

    fun getUsersFromLocalDatabase(): Flow<List<User>> =
        flow {
            emit(appDao.getAllUsers())
        }

}