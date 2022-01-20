package com.example.sobok_android.presentation.view.calendar.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.presentation.view.calendar.CalendarDayListData
import com.example.sobok_android.presentation.view.calendar.CalendarView.Companion.FIRST_POSITION
import com.example.sobok_android.presentation.view.calendar.MonthView
import com.example.sobok_android.presentation.view.calendar.adapter.CalendarMonthViewPagerAdapter.Companion.MAX_ITEM_COUNT
import java.text.SimpleDateFormat
import java.util.*

class CalendarWeekViewPagerAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<CalendarWeekViewPagerAdapter.CalendarViewHolder>() {
    //리스너 달기
    private var postSelectDate: ((String) -> Unit)? = null

    fun setPostSelectData(listener: (String) -> Unit) {
        postSelectDate = listener
    }

    private var _isMonth : Boolean = true
    fun setIsMonth(value: Boolean) {
        _isMonth = value
    }

    private val completeDateList = MutableLiveData<List<CalendarDayListData.DayInfo>>()

    fun setCompleteDateList(value: List<CalendarDayListData.DayInfo>) {
        completeDateList.postValue(value)
    }


    override fun getItemCount(): Int = MAX_ITEM_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(MonthView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        })
    }

    override fun onBindViewHolder(
        holder: CalendarViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    inner class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {

            view.dateRecyclerView.adapter = CalendarDateRecyclerViewAdapter().apply {
                isMonth = _isMonth
                completeDateList.observe(lifecycleOwner) {
                    this.setCompleteDateList( completeDateList.value ?: listOfNotNull(), 0, 7)
                }
                    setWeekCalendar(Calendar.getInstance(Locale.KOREA).apply {
                        add(Calendar.DAY_OF_MONTH, (position - FIRST_POSITION) * 7) // 7일을 더하면 됨.....
                    })
                    Log.d("point/dateRecyclerViewWeek", "$_isMonth")
                setPostSelectData {
                    postSelectDate?.invoke(it)
                    Log.d("please/CalendarViewPagerAdpater", "$it")
                }
            }

        }
    }
}