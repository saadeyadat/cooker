package com.example.itemreminder.other.register

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import com.example.itemreminder.model.User
import com.example.itemreminder.model.database.Repository
import com.example.itemreminder.viewModel.UsersViewModel

class AppSignIn(val context: Context) {

    fun checkUser(allUsers: List<User>, email: String, password: String): Boolean {
        var result = false
        for (user in allUsers)
            if (user.email == email)
                if (user.password == password)
                    result = true
                else
                    Toast.makeText(context, "Invalid Password.", Toast.LENGTH_SHORT).show()
        if (!result)
            Toast.makeText(context, "User Does Not Exist.", Toast.LENGTH_SHORT).show()
        return result
    }
}