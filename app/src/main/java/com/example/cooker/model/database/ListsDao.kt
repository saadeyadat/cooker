package com.example.cooker.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cooker.model.Lists

@Dao
interface ListsDao {

    @Insert
    fun addList(list: Lists)

    @Delete
    fun deleteList(list: Lists)

    @Update
    fun updateList(list: Lists)

    @Query("Select * from allLists")
    fun getAllLists(): LiveData<List<Lists>>
}