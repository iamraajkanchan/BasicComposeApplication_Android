package com.chinky.family.domain.usecase

import com.chinky.family.data.repository.ToDoRepository
import com.chinky.family.domain.model.ToDoItem
import javax.inject.Inject

class HandleToDoUseCase @Inject constructor(private val repository: ToDoRepository) {

    suspend fun insertTodo(todo: ToDoItem) {
        repository.insertTodo(todo)
    }

    suspend fun getAllTodos(): List<ToDoItem> {
        return repository.getAllTodos()
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