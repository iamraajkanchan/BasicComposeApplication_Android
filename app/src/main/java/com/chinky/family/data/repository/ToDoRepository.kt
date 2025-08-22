package com.chinky.family.data.repository

import com.chinky.family.data.db.AppDao
import com.chinky.family.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val appDao: AppDao) {

    suspend fun insertTodo(todo: ToDoItem) {
        appDao.insertTodo(todo)
    }

    fun getAllTodos(): Flow<List<ToDoItem>> = flow {
        emit(appDao.getAllTodos())
    }

    suspend fun getTodoById(id: Int): ToDoItem? {
        return appDao.getTodoById(id)
    }

    suspend fun deleteTodo(todoID: Int) {
        appDao.deleteTodo(todoID)
    }

    suspend fun updateTodo(todo: ToDoItem) {
        appDao.updateTodo(todo)
    }

}