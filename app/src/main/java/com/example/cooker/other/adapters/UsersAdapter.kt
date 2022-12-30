package com.example.cooker.other.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooker.R
import com.example.cooker.model.User

class UsersAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val users_table_name: TextView
        val users_table_email: TextView
        val users_table_level: TextView
        init {
            users_table_name = view.findViewById(R.id.users_table_name)
            users_table_email = view.findViewById(R.id.users_table_email)
            users_table_level = view.findViewById(R.id.users_table_level)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.users_adapter, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (users[position].name.contains(' '))
            viewHolder.users_table_name.text = users[position].name.split(' ')[1]
        else
            viewHolder.users_table_name.text = users[position].name
        viewHolder.users_table_email.text = users[position].email.split('@')[0] + "(" + users[position].email.split('@')[1].split('.')[0] + ")"
        viewHolder.users_table_level.text = users[position].type
    }

    override fun getItemCount() = users.size
}