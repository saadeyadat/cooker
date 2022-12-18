package com.example.itemreminder.other.adapters

import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itemreminder.model.Item
import com.example.itemreminder.model.database.Repository
import com.example.itemreminder.R
import com.example.itemreminder.model.User
import com.example.itemreminder.other.managers.FirebaseManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParticipantsAdapter(private val participantsList: MutableList<String>, private val usersList: List<User>) : RecyclerView.Adapter<ParticipantsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val participant_image: ImageView
        init {
            participant_image = view.findViewById(R.id.participants_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.participants_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (user in usersList)
            if (participantsList[position].isNotEmpty())
                if (participantsList[position] == user.email)
                    if (user.image!!.isNotEmpty())
                        holder.participant_image.setImageURI(Uri.parse(user.image))
    }

    override fun getItemCount(): Int {
        return participantsList.size
    }
}