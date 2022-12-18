package com.example.itemreminder.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.itemreminder.model.Converters
import com.example.itemreminder.model.Lists
import com.example.itemreminder.model.Item
import com.example.itemreminder.model.User

@Database(entities = arrayOf(Item::class, User::class, Lists::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getItemDao(): ItemsDao
    abstract fun getUserDao(): UsersDao
    abstract fun getListsDao(): ListsDao

    companion object {
        fun getDatabase(context: Context?): AppDatabase {
            return Room.databaseBuilder(
                context!!.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
        }
    }
}