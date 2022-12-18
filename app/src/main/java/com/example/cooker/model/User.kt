package com.example.cooker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersTable")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "lists")
    var lists: String? = "",
    @ColumnInfo(name = "image")
    var image: String? = ""
)