package com.chinky.family.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chinky.family.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}