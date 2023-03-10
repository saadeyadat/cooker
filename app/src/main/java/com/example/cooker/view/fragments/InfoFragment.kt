package com.example.cooker.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cooker.model.Item
import com.example.cooker.R
import com.example.cooker.other.adapters.InfoAdapter
import com.example.cooker.model.database.Repository
import kotlinx.android.synthetic.main.info_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InfoFragment(private val item: Item, context: Context): Fragment(R.layout.info_fragment) {

    override fun onResume() {
        super.onResume()

        infoRecyclerView()
        add_button?.setOnClickListener {
            item.info += add_text?.text.toString() + ','
            GlobalScope.launch { Repository.getInstance(context).updateItemInfo(item, item.info) }
            add_text?.setText("")
            infoRecyclerView()
        }

        exit_button2?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun infoRecyclerView() {
        val infoAdapter = InfoAdapter(item, context)
        infoRecyclerView?.adapter = infoAdapter
    }
}