package com.example.cooker.other.adapters

import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cooker.model.Item
import com.example.cooker.model.database.Repository
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.other.managers.FirebaseManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemsAdapter(private val list: List,
                   private val currentUserEmail: String,
                   private val context: Context,
                   val updateItemImage: (Item) -> Unit,
                   val displayFruitFragment: (Item) -> Unit): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private var dataList = mutableListOf<Item>()
    fun setList(itemList: MutableList<Item>) {
        this.dataList = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val item_name: TextView
        val item_image: ImageView
        val delete: ImageButton
        init {
            item_name = view.findViewById(R.id.item_name)
            item_image = view.findViewById(R.id.item_image)
            delete = view.findViewById(R.id.delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item_name.text = dataList[position].name

        if (dataList[position].image!!.contains("https://"))
            Glide.with(context).load(dataList[position].image).into(holder.item_image)
        else
            holder.item_image.setImageURI(Uri.parse(dataList[position].image))

        holder.delete.setOnClickListener {
            if (currentUserEmail == list.owner.split("-")[0])
                displayAlert(context, position)
            else
                Toast.makeText(context, "Only Owner Allowed To Edit This Field.", Toast.LENGTH_SHORT).show()
        }

        holder.item_name.setOnClickListener {
            displayFruitFragment(dataList[position])
        }

        holder.item_image.setOnClickListener {
            if (currentUserEmail == list.owner.split("-")[0])
                updateItemImage(dataList[position])
            else
                Toast.makeText(context, "Only Owner Allowed To Edit This Field.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun displayAlert(context: Context, position: Int) {
        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setTitle("Delete Item")
        alertBuilder.setMessage("You Will Delete '${dataList[position].name}':  ")
        alertBuilder.setNeutralButton("Cancel") { dialogInterface: DialogInterface, i: Int -> }
        alertBuilder.setPositiveButton("Delete") { dialogInterface: DialogInterface, i: Int ->
            GlobalScope.launch {
                Repository.getInstance(context).deleteItem(dataList[position])
                FirebaseManager.getInstance(context).deleteItem(dataList[position])
                dataList.remove(dataList[position])
            }
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }
        alertBuilder.show()
    }
}