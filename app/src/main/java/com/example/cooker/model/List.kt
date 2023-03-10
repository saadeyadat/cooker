package com.example.cooker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "allLists")
data class List(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "parameters") val parameters: String,
    @ColumnInfo(name = "image") var image: String? = "",
    @ColumnInfo(name = "participants") var participants: String? = ""
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}