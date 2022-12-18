package com.example.cooker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cooker.model.Lists
import com.example.cooker.model.database.Repository

class ListsViewModel(val app: Application): AndroidViewModel(app) {

    private val repository = Repository.getInstance(app.applicationContext)
    val listsData: LiveData<List<Lists>> = repository.getAllLists()

    fun addList(lists: Lists) {
        repository.addList(lists)
    }
}