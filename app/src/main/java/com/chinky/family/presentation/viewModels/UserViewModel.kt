package com.chinky.family.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinky.family.domain.model.User
import com.chinky.family.domain.usecase.HandleUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: HandleUserUseCase) : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadUsers() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val userList = userUseCase.getUsers()
                _users.value = userList
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createUser(user: User) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                userUseCase.createUser(user)
                loadUsers()
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                userUseCase.updateUser(user)
                loadUsers()
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                val success = userUseCase.deleteUser(id)
                if (success) {
                    // Update the local list immediately for better UX
                    _users.value = _users.value?.filter { it.id != id }
                    _error.value = null
                } else {
                    _error.value = "Failed to delete user"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }


}