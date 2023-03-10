package com.example.cooker.model.database

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.cooker.model.List
import com.example.cooker.model.Item
import com.example.cooker.model.User
import com.example.cooker.other.managers.FirebaseManager

class Repository private constructor(application: Context?) {

    private val itemDao = AppDatabase.getDatabase(application).getItemDao()
    private val userDao = AppDatabase.getDatabase(application).getUserDao()
    private val listsDao = AppDatabase.getDatabase(application).getListsDao()
    private val firebase = FirebaseManager.getInstance(application!!)

    companion object {
        private lateinit var instance: Repository
        fun getInstance(application: Context?): Repository {
            if (!::instance.isInitialized)
                instance = Repository(application)
            return instance
        }
    }

    //------------- User Functions -------------//
    fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun addUserList(user: User, list: String) {
        user.lists += "-$list"
        updateUser(user)
    }

    private fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    fun updateUserImage(user: User, image: String) {
        user.image = image
        updateUser(user)
    }

    fun getAllUsers(): LiveData<kotlin.collections.List<User>> {
        return userDao.getAllUsers()
    }

    fun addUserImageToFB(uri: Uri, currentUser: User) {
        firebase.addUserImage(uri, currentUser)
    }

    fun clearDB() {
        userDao.clearDB()
    }

    //------------- Lists Functions -------------//
    fun addList(list: List) {
        listsDao.addList(list)
    }

    fun deleteList(list: List) {
        listsDao.deleteList(list)
    }

    private fun updateList(list: List) {
        listsDao.updateList(list)
    }

    fun getAllLists(): LiveData<kotlin.collections.List<List>> {
        return listsDao.getAllLists()
    }

    fun addParticipant(list: List, participant: String) {
        list.participants += "$participant-"
        updateList(list)
    }

    fun updateParticipants(list: List, participants: String) {
        list.participants = participants
        updateList(list)
    }

    //------------- Item Functions -------------//
    fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    private fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    fun updateItemInfo(item: Item, info: String) {
        item.info = info
        updateItem(item)
    }

    fun updateItemImage(item: Item, image: String) {
        item.image = image
        updateItem(item)
    }

    fun getLiveDataAllItems(): LiveData<kotlin.collections.List<Item>> {
        return itemDao.getAllItems()
    }
}