package com.example.cooker.other.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.cooker.R
import com.example.cooker.model.Lists
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import com.example.cooker.view.activities.ItemsActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListsAdapter(private val context: Context, private val user: User): RecyclerView.Adapter<ListsAdapter.ViewHolder>() {

    private var lists = mutableListOf<Lists>()
    fun setList(list: MutableList<Lists>) {
        this.lists = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val list_name: TextView
        val list_owner: TextView
        val delete: ImageView
        init {
            list_name = view.findViewById(R.id.item_name)
            list_owner = view.findViewById(R.id.list_owner)
            delete = view.findViewById(R.id.delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lists_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.list_name.text = lists[position].name.split("-")[1]
        holder.list_owner.text = "Owner: " + lists[position].owner.split("-")[0]

        holder.list_name.setOnClickListener {
            val intent = Intent(context, ItemsActivity::class.java)
            intent.putExtra("listID", lists[position].name)
            intent.putExtra("userEmail", user.email)
            context.startActivity(intent)
        }

        holder.delete.setOnClickListener {
            if (lists[position].owner.split("-")[0] == user.email)
                displayAlert(context, position)
            else
                Toast.makeText(context, "Only Recipe Owner Allow To Edit This Field.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    private fun displayAlert(context: Context, position: Int) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setTitle("Delete Item")
        alertBuilder.setMessage("You Will Delete '${lists[position].name.split("-")[0]}':  ")
        alertBuilder.setNeutralButton("Cancel") { dialogInterface: DialogInterface, i: Int -> }
        alertBuilder.setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
            GlobalScope.launch {
                FirebaseManager.getInstance(context).deleteList(lists[position])
                Repository.getInstance(context).deleteList(lists[position])
                lists.remove(lists[position])
            }
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }
        alertBuilder.show()
    }
}