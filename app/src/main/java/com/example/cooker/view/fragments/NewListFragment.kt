package com.example.cooker.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import kotlinx.android.synthetic.main.item_fragment_info.add_button
import kotlinx.android.synthetic.main.item_fragment_info.exit_button2
import kotlinx.android.synthetic.main.new_list_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewListFragment(private val user: User, context: Context): Fragment(R.layout.new_list_fragment) {

    override fun onResume() {
        super.onResume()
        add_button?.setOnClickListener {
            val list = list_name.text.toString()
            GlobalScope.launch {
                Repository.getInstance(context).addUserList(user, list)
                Repository.getInstance(context).addList(List("${user.email}-$list", "${user.email}-${user.name}"))
                //FirebaseManager.getInstance(requireContext()).addList(List("${user.email}-$list", "${user.email}-${user.name}"))
                //NotificationsManager.newList(requireContext(), user.name)
            }
            list_name?.setText("")
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        exit_button2?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}