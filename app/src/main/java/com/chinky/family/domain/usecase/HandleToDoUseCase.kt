package com.chinky.family.domain.usecase

import com.chinky.family.data.repository.ToDoRepository
import com.chinky.family.domain.model.ToDoItem
import com.chinky.family.domain.utils.printLogcat
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class HandleToDoUseCase @Inject constructor(private val repository: ToDoRepository) {

    suspend fun insertTodo(todo: ToDoItem) {
        repository.insertTodo(todo)
    }

    suspend fun getAllTodos(): List<ToDoItem> {
        var todos = listOf<ToDoItem>()
        repository.getAllTodos().collectLatest {
            todos = it
        }
        return todos
    }

    suspend fun getTodoById(id: Int): ToDoItem? {
        return repository.getTodoById(id)
    }

    suspend fun deleteTodo(todoID: Int) {
        repository.deleteTodo(todoID)
    }

    suspend fun updateTodo(todo: ToDoItem) {
        repository.updateTodo(todo)
    }

}