package com.chinky.family.presentation.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinky.family.domain.model.User
import com.chinky.family.domain.usecase.HandleUserUseCase
import com.chinky.family.presentation.ui.networkCall.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: HandleUserUseCase) : ViewModel() {
    private val _users: MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    val users: State<ApiState> = _users
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _error : MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    val error: State<ApiState> = _error

    fun loadUsers() {
        viewModelScope.launch {
            _users.value = ApiState.Loading
            try {
                val userList = userUseCase.getUsers()
                _users.value = ApiState.Success(userList)
            } catch (e: Exception) {
                _users.value = ApiState.Failure(e)
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
                _error.value = ApiState.Failure(e)
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
                _error.value = ApiState.Failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                userUseCase.deleteUser(id).collectLatest { updatedUsers ->
                    _users.value = ApiState.Success(updatedUsers)
                    // Correctly indicate a successful operation with no error
                    _error.value = ApiState.Failure(null)
                }
            } catch (e: Exception) {
                _error.value = ApiState.Failure(e)
            }
        }
    }


}