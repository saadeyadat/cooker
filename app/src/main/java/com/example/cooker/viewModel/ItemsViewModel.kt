package com.example.cooker.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cooker.model.Item
import com.example.cooker.model.database.Repository

class ItemsViewModel(val app: Application): AndroidViewModel(app) {

    private val repository = Repository.getInstance(app.applicationContext)
    val itemsData: LiveData<List<Item>> = repository.getLiveDataAllItems()

    fun addItem(item: Item) {
        repository.addItem(item)
    }
}