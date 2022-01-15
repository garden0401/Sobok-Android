package com.example.sobok_android.util

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
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