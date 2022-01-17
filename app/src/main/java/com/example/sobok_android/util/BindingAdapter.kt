package com.example.sobok_android.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.sobok_android.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("pillListColor")
    fun setPillColor(imageView: ImageView, color: String) {
        with(imageView) {
            when (color.toInt()) {
                1 -> {
                    setImageResource(R.drawable.oval_pill_list_color_red)
                    visibility = View.VISIBLE

                }
                2 -> {
                    setImageResource(R.drawable.oval_pill_list_color_orange)
                    visibility = View.VISIBLE
                }
                3 -> {
                    setImageResource(R.drawable.oval_pill_list_color_purple)
                    visibility = View.VISIBLE
                }
                4 -> {
                    setImageResource(R.drawable.oval_pill_list_color_blue)
                    visibility = View.VISIBLE
                }
                5 -> {
                    setImageResource(R.drawable.oval_pill_list_color_pink)
                    visibility = View.VISIBLE
                }
            }
        }
    }
}