package com.example.cooker.view.fragments

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cooker.R
import com.example.cooker.model.User
import com.example.cooker.view.activities.ItemsActivity
import kotlinx.android.synthetic.main.new_participant_fragment.*
import kotlinx.android.synthetic.main.user_data_fragment.*

class UserDataFragment(private val user: User, private val context: Context): Fragment(R.layout.user_data_fragment) {

    override fun onResume() {
        super.onResume()

        if (user.image!!.isNotEmpty())
            if (user.image!!.contains("https://"))
                Glide.with(context).load(user.image).into(user_data_image)
            else
                user_data_image.setImageURI(Uri.parse(user.image))
        user_data_email.text = user.email

        user_data_exit?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}