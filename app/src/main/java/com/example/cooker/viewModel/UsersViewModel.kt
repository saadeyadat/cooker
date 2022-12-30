package com.example.cooker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository

class UsersViewModel(val app: Application): AndroidViewModel(app) {

    private val repository = Repository.getInstance(app.applicationContext)
    val usersData: LiveData<List<User>> = repository.getAllUsers()
}