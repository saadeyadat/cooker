package com.example.cooker.view.fragments

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.model.User
import com.example.cooker.view.activities.ListsActivity
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.new_list_fragment.*
import kotlinx.android.synthetic.main.new_list_fragment.BBQ_switch
import kotlinx.android.synthetic.main.new_list_fragment.events_food
import kotlinx.android.synthetic.main.new_list_fragment.fastFood_switch
import kotlinx.android.synthetic.main.new_list_fragment.home_made
import kotlinx.android.synthetic.main.new_list_fragment.salad_switch
import kotlinx.android.synthetic.main.new_list_fragment.shakes_switch


class FilterFragment(private val user: User, private val userLists: MutableList<List>) : Fragment(R.layout.filter_fragment) {

    private var parameters = ""
    private val filteredLists = mutableListOf<List>()
    override fun onResume() {
        super.onResume()
        readSwitches()
        filter_add_button?.setOnClickListener {
            if (parameters != "") {
                val filterParamArr = parameters.split(',')
                for (list in userLists) {
                    val listParamArr = list.parameters.split(',')
                    for (listParam in listParamArr)
                        if (filterParamArr.contains(listParam) && listParam!="")
                            if (!filteredLists.contains(list))
                                filteredLists.add(list)
                }
            }
            var filterListsStr = ""
            if (filteredLists.isNotEmpty())
                for (list in filteredLists)
                    filterListsStr += "${list.name.split('-')[1]},"
            if (filterListsStr == "")
                filterListsStr = "empty"
            val intent = Intent(requireActivity().baseContext, ListsActivity::class.java)
            intent.putExtra("userEmail", user.email)
            intent.putExtra("filter", filterListsStr)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().startActivity(intent)
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        reset_button.setOnClickListener {
            val intent = Intent(requireActivity().baseContext, ListsActivity::class.java)
            intent.putExtra("userEmail", user.email)
            intent.putExtra("filter", "")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            requireActivity().startActivity(intent)
        }

        filter_exit_button?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun readSwitches() {
        salad_switch.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "Salad,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("Salad"))
                    paramArr.remove("Salad")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
        BBQ_switch.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "BBQ,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("BBQ"))
                    paramArr.remove("BBQ")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
        fastFood_switch.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "FastFood,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("FastFood"))
                    paramArr.remove("FastFood")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
        shakes_switch.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "Shakes,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("Shakes"))
                    paramArr.remove("Shakes")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
        home_made.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "Home,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("Home"))
                    paramArr.remove("Home")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
        events_food.setOnCheckedChangeListener { _, onSwtich ->
            if (onSwtich)
                parameters += "Events,"
            else {
                val paramArr: MutableList<String> = parameters.split(',') as MutableList<String>
                parameters = ""
                if (paramArr.contains("Events"))
                    paramArr.remove("Events")
                for (param in paramArr)
                    parameters += "$param,"
            }
        }
    }
}