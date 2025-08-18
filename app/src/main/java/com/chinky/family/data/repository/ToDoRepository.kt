package com.chinky.family.data.repository

import com.chinky.family.data.db.AppDao
import com.chinky.family.domain.model.ToDoItem
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val appDao: AppDao) {

    suspend fun insertTodo(todo: ToDoItem) {
        appDao.insertTodo(todo)
    }

    suspend fun getAllTodos(): List<ToDoItem> {
        return appDao.getAllTodos()
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