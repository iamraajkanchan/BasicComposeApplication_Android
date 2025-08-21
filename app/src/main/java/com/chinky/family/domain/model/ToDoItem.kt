package com.chinky.family.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoTable")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val todoID: Int = 0,
    val title: String,
    val description: String,
    val date: String
)
