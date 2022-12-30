package com.example.cooker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruitsTable")
data class Item(
    @ColumnInfo(name = "list") val list: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") var image: String? = "",
    @ColumnInfo(name = "info") var info: String,
    ){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}