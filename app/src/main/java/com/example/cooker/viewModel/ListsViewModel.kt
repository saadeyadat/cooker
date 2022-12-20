package com.example.cooker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cooker.model.List
import com.example.cooker.model.database.Repository

class ListsViewModel(val app: Application): AndroidViewModel(app) {

    private val repository = Repository.getInstance(app.applicationContext)
    val listsData: LiveData<kotlin.collections.List<List>> = repository.getAllLists()

    fun addList(lists: List) {
        repository.addList(lists)
    }
}