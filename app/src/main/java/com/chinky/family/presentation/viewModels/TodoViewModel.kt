package com.chinky.family.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinky.family.domain.model.ToDoItem
import com.chinky.family.domain.state.ToDoState
import com.chinky.family.domain.usecase.HandleToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val toDoUseCase: HandleToDoUseCase) : ViewModel() {
    private val _todos = mutableStateOf<ToDoState>(ToDoState.Loading)
    val todos: State<ToDoState> = _todos

    init {
        loadTodos()
    }

    fun loadTodos() {
        _todos.value = ToDoState.Loading
        viewModelScope.launch {
            try {
                _todos.value = ToDoState.Success(toDoUseCase.getAllTodos())
            } catch (e: Exception) {
                _todos.value = ToDoState.Error(e)
            }
        }
    }

    fun insertTodo(todo: ToDoItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toDoUseCase.insertTodo(todo)
            }
        }
    }

    fun deleteTodo(todoID: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toDoUseCase.deleteTodo(todoID)
            }
        }
    }

    fun updateTodo(todo: ToDoItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                toDoUseCase.updateTodo(todo)
            }
        }
    }
}