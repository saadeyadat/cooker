package com.example.cooker.other.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooker.R
import com.example.cooker.model.Item
import com.example.cooker.model.User
import kotlinx.android.synthetic.main.menu_header.*

class ParticipantsAdapter(
    private val participantsList: MutableList<String>,
    private val usersList: List<User>,
    private val context: Context,
    val displayUserDataFragment: (User) -> Unit) : RecyclerView.Adapter<ParticipantsAdapter.ViewHolder>() {

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
                        if (user.image!!.contains("https://"))
                            Glide.with(context).load(user.image).into(holder.participant_image)
                        else
                            holder.participant_image.setImageURI(Uri.parse(user.image))

        holder.participant_image.setOnClickListener {
            for (user in usersList)
                if (participantsList[position].isNotEmpty())
                    if (participantsList[position] == user.email)
                        displayUserDataFragment(user)
        }
    }

    override fun getItemCount(): Int {
        return participantsList.size
    }
}