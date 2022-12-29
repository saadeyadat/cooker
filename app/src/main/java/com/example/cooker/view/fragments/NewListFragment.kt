package com.example.cooker.view.fragments

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cooker.R
import com.example.cooker.model.List
import com.example.cooker.model.User
import com.example.cooker.model.database.Repository
import com.example.cooker.other.managers.FirebaseManager
import com.example.cooker.other.managers.NotificationsManager
import kotlinx.android.synthetic.main.new_list_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewListFragment(private val user: User, context: Context): Fragment(R.layout.new_list_fragment) {

    private var parameters = ""
    override fun onResume() {
        super.onResume()
        readSwitches(user)
        add_button?.setOnClickListener {
            val list = list_name.text.toString()
            if (list.isNotEmpty())
                GlobalScope.launch {
                    Repository.getInstance(context).addUserList(user, list)
                    Repository.getInstance(context).addList(List("${user.email}-$list", "${user.email}-${user.name}", parameters))
                    FirebaseManager.getInstance(requireContext()).addList(List("${user.email}-$list", "${user.email}-${user.name}", parameters))
                }
            else
                Toast.makeText(requireContext(), "Please Enter Meal Name.", Toast.LENGTH_SHORT).show()
            list_name?.setText("")
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        exit_button2?.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    private fun readSwitches(user: User) {
        if (user.type != "master") {
            home_made.isClickable = false
            events_food.isClickable = false
        }
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
            if (user.type == "master")
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
            if (user.type == "master")
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