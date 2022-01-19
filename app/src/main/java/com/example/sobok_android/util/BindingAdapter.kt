package com.example.sobok_android.util
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.sobok_android.R
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
    @JvmStatic
    @BindingAdapter("pillTime")
    fun setPillTime(textView: TextView, pillList: List<String>) {
        var times = ""
        val size = pillList.size

        Log.d("bindding-adapter", "${pillList.size}")
        (0 until size-1).forEach { i: Int ->
            if (i == 3) {
                times += "\n"
            }
            times += pillList[i]
            times += ", "
        }
        times += pillList[size-1]

        textView.text = times
        Log.d("bindding-adapter", "$times")
    }
}