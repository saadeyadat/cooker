package com.example.cooker.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cooker.model.Item

@Dao
interface ItemsDao {

    @Insert
    fun addItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("Select * from fruitsTable")
    fun getAllItems(): LiveData<List<Item>>

    @Update
    fun updateItem(item: Item)
}