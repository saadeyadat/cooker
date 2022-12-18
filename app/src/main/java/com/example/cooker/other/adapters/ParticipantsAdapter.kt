package com.example.cooker.other.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooker.R
import com.example.cooker.model.User

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