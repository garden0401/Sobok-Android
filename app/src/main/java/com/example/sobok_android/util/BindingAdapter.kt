package com.example.sobok_android.util
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.sobok_android.R
import android.util.Log
import android.widget.TextView
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("pillListColor")
    fun setPillColor(imageView: ImageView, color: String?) {
        with(imageView) {
            when (color?.toInt()) {
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

    @JvmStatic
    @BindingAdapter("isCalendar", "isButtonClick")
    fun setNoticeListImage(imageView: ImageView, isCalendar: Boolean, isButtonClick: Boolean) {
        with(imageView) {
            if(isCalendar && isButtonClick) {
                setImageResource(R.drawable.ic_calendar_alarm_gray)
            }else if(isCalendar && !isButtonClick) {
                setImageResource(R.drawable.ic_calendar_alarm_mint)
            }else if(!isCalendar && isButtonClick) {
                setImageResource(R.drawable.ic_fill_alarm_gray)
            } else if(!isCalendar && !isButtonClick) {
                setImageResource(R.drawable.ic_fill_alarm_mint)
            }
        }
    }




    @JvmStatic
    @BindingAdapter("stickerCount")
    fun setStickerImage(imageView: ImageView, stickerCount: Int) {
        with(imageView) {
            when (stickerCount) {
                1 -> {
                    setImageResource(R.drawable.ic_sticker_1_aos)
                }
                2 -> {
                    setImageResource(R.drawable.ic_sticker_2_aos)
                }
                3 -> {
                    setImageResource(R.drawable.ic_sticker_3_aos)
                }
                4 -> {
                    setImageResource(R.drawable.ic_sticker_4_aos)
                }
                5 -> {
                    setImageResource(R.drawable.ic_sticker_5_aos)
                }
                6 -> {
                    setImageResource(R.drawable.ic_sticker_6_aos)
                }
            }
        }
    }
}