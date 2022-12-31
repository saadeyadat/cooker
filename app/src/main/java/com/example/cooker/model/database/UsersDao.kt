package com.example.cooker.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.cooker.model.User

@Dao
interface UsersDao {

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM usersTable")
    fun clearDB()

    @Query("Select * from usersTable")
    fun getAllUsers(): LiveData<List<User>>
}