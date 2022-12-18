package com.example.cooker.other.register

import android.content.Context
import android.widget.Toast
import com.example.cooker.model.User

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