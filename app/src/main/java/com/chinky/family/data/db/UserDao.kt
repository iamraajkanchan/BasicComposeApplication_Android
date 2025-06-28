package com.chinky.family.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.chinky.family.domain.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * from UserTable")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * from UserTable WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Query("DELETE FROM UserTable")
    suspend fun deleteAllUsers()

    @Update
    suspend fun updateUser(user: User)

    @Query ("DELETE FROM UserTable WHERE id = :id")
    suspend fun deleteUser(id: Int)

}