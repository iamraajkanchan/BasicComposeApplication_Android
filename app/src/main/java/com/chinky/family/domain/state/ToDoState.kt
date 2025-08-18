package com.chinky.family.domain.state

import com.chinky.family.domain.model.ToDoItem

open class ToDoState {
    object Loading : ToDoState()
    data class Success(val data: List<ToDoItem>) : ToDoState()
    data class Error(val error: Throwable?) : ToDoState()
}