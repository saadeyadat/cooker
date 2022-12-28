package com.example.cooker.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cooker.R
import com.example.cooker.model.User
import com.example.cooker.other.adapters.UsersAdapter
import com.example.cooker.viewModel.UsersViewModel
import kotlinx.android.synthetic.main.users_activity.*

class UsersActivity() : AppCompatActivity() {

    private val usersViewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        usersRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun usersRecyclerView() {
        usersViewModel.usersData.observe(this) {
            val adapter = UsersAdapter(it)
            users_recyclerview.adapter = adapter
        }
    }
}