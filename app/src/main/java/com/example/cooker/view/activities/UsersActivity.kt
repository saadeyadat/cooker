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

    private var maxPageCount = 8
    private val users = mutableListOf<User>()
    private val usersViewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_activity)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        load_more.setOnClickListener { pagination() }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun pagination() {
        usersViewModel.usersData.observe(this) {
            var usersInPage = 1
            for (user in it) {
                if (!users.contains(user))
                    users.add(user)
                if (usersInPage % maxPageCount == 0 || usersInPage >= it.size) {
                    usersRecyclerView(users)
                    maxPageCount += maxPageCount
                    break
                }
                usersInPage++
            }
        }
    }

    private fun usersRecyclerView(users: MutableList<User>) {
        val adapter = UsersAdapter(users)
        users_recyclerview.adapter = adapter
    }
}