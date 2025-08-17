package com.chinky.family.presentation.ui.networkCall

import com.chinky.family.domain.model.User

sealed class ApiState {
    class Success(val data: List<User>) : ApiState()
    class Failure(val error: Throwable?) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}