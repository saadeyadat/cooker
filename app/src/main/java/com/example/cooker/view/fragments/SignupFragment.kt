package com.example.cooker.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cooker.R
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import com.example.cooker.view.activities.LoginActivity
import kotlinx.android.synthetic.main.signup_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignupFragment(context: Context, private val allUsers: List<User>) : Fragment() {

    private var type = "beginner"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.signup_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        setBottomMenu()
        readSwitch()
        signup_button.setOnClickListener { createUser() }
    }

    private fun createUser() {
        val name = signup_name?.text.toString()
        val email = signup_email?.text.toString()
        val password1 = signup_password1?.text.toString()
        val password2 = signup_password2?.text.toString()
        if (checkName(name) && checkEmail(email) && checkPassword(password1, password2)) {
            regToDatabase(name, type, email, password1)
            regToFirebase(name, type, email, password1)
            Toast.makeText(requireContext(), "Signup Successfully.", Toast.LENGTH_SHORT).show()
            (activity as LoginActivity?)!!.addUsers()
        }
    }

    private fun readSwitch() {
        type_switch.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                type = "master"
            else
                type = "beginner"
        }
    }

    private fun checkName(name: String): Boolean {
        if (name.isNotEmpty())
            return true
        Toast.makeText(requireContext(), "Please Enter Valid Name.", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun checkEmail(email: String): Boolean {
        for (user in allUsers)
            if (user.email == email) {
                Toast.makeText(requireContext(), "User Already Exist.", Toast.LENGTH_SHORT).show()
                return false
            }
        if (email.contains('@') && email.length>7 && email!="")
            return true
        Toast.makeText(requireContext(), "Please Enter Valid Email.", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun checkPassword(password1: String, password2: String): Boolean {
        if (password1==password2 && password1!="")
            if ( password1.length>=8)
                return true
            else
                Toast.makeText(requireContext(), "Password must be at least 8 digits.", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), "Passwords must be the same.", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun regToDatabase(name: String, type: String, email: String, password: String) {
        GlobalScope.launch { Repository.getInstance(context).addUser(User(email, password, name, type)) }
    }

    private fun regToFirebase(name: String, type: String, email: String, password: String) {
        FirebaseManager.getInstance(requireContext()).addUser(User(email, password, name, type))
    }

    private fun setBottomMenu() {
        signup_bottom_menu2.setTextColor(Color.parseColor("#0835C5"))
        login_bottom_menu2.setTextColor(Color.parseColor("#000000"))
        login_bottom_menu2.setOnClickListener {
            signup_bottom_menu2.setTextColor(Color.parseColor("#000000"))
            login_bottom_menu2.setTextColor(Color.parseColor("#0835C5"))
            (activity as LoginActivity?)!!.setBottomMenu()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}