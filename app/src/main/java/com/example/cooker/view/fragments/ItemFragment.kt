package com.example.cooker.view.fragments

import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cooker.R
import com.example.cooker.model.Item
import kotlinx.android.synthetic.main.item_fragment.*

class ItemFragment(private val item: Item, context: Context): Fragment(R.layout.item_fragment) {

    private val itemFragmentInfo = ItemFragmentInfo(item, context)
    override fun onResume() {
        super.onResume()

        val name = requireArguments().getString("fruitName")
        val image = requireArguments().getString("fruitImage")

        fruit_title?.text = name.toString()
        fruit_image?.setImageURI(Uri.parse(image))
        if (image!!.contains("https://"))
            Glide.with(requireContext()).load(image).into(fruit_image)
        else
            fruit_image?.setImageURI(Uri.parse(image))

        fruit_info?.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragment_view_info, itemFragmentInfo).commit()
        }

        exit_button?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }
}