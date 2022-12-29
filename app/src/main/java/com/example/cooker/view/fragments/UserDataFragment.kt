package com.example.cooker.view.fragments

import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import kotlinx.android.synthetic.main.new_participant_fragment.*
import kotlinx.android.synthetic.main.user_data_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDataFragment(private val user: User): Fragment(R.layout.user_data_fragment) {

    override fun onResume() {
        super.onResume()

        if (user.image!!.isNotEmpty())
            user_data_image.setImageURI(Uri.parse(user.image))
        user_data_email.text = user.email

        user_data_exit?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}