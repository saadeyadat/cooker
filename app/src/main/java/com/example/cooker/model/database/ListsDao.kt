package com.example.cooker.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cooker.model.List

@Dao
interface ListsDao {

    @Insert
    fun addList(list: List)

    @Delete
    fun deleteList(list: List)

    @Update
    fun updateList(list: List)

    @Query("Select * from allLists")
    fun getAllLists(): LiveData<kotlin.collections.List<List>>
}