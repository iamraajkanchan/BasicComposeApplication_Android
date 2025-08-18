package com.chinky.family.domain.state

import com.chinky.family.domain.model.User

sealed class UserApiState {
    class Success(val data: List<User>) : UserApiState()
    class Failure(val error: Throwable?) : UserApiState()
    object Loading : UserApiState()
    object Empty : UserApiState()
}