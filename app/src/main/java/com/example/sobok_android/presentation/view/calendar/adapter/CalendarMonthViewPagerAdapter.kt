package com.example.sobok_android.presentation.view.calendar.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.presentation.view.calendar.CalendarDayListData
import com.example.sobok_android.presentation.view.calendar.CalendarView.Companion.FIRST_POSITION
import com.example.sobok_android.presentation.view.calendar.MonthView
import java.util.*

class CalendarMonthViewPagerAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<CalendarMonthViewPagerAdapter.CalendarViewHolder>() {

    private var postSelectDate: ((String) -> Unit)? = null

    fun setPostSelectData(listener: (String) -> Unit) {
        postSelectDate = listener
    }

    private var _isMonth: Boolean = true
    fun setIsMonth(value: Boolean) {
        _isMonth = value
    }

    private var postSendCalendar: ((Calendar) -> Unit)? = null

    fun setPostSendCalendar(listener: (Calendar) -> Unit) {
        postSendCalendar = listener
    }


    private val completeDateList = MutableLiveData<CalendarDayListData>()

    fun setCompleteDateList(value: CalendarDayListData) {
        completeDateList.postValue(value)
    }

    var sendCalendar: Calendar = Calendar.getInstance(Locale.KOREA)

    private var sendDateGetter: ((Calendar) -> Unit)? = null
    fun setSendDateGetter(listener: (Calendar) -> Unit) {
        sendDateGetter = listener
    }

    private var isSetPosition: Boolean = false


    var currentPosition: Int = 5

    fun setCurrentPostion(value: Int) {
        Log.d("currentPostion-setting", "$${value}")
        isSetPosition = true
        currentPosition = value
        notifyItemChanged(currentPosition)  // ?????????
    }


    override fun getItemCount(): Int = MAX_ITEM_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val monthView = MonthView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return CalendarViewHolder(monthView)
    }

    override fun onBindViewHolder(
        holder: CalendarViewHolder,
        position: Int
    ) {
        Log.d("check/position", "${position}")
        //holder.setIsRecyclable(false)
        holder.bind(position)
    }

    inner class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            view.dateRecyclerView.adapter = CalendarDateRecyclerViewAdapter().apply {
                isMonth = _isMonth


                sendCalendar = Calendar.getInstance(Locale.KOREA).apply {
                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
                    add(Calendar.MONTH, currentPosition - FIRST_POSITION) // 좌우 스와이프 시 월 +-1 계산
                }
                postSendCalendar?.invoke(sendCalendar)
                setMonthCalendar(sendCalendar)
                isSetPosition = false

                completeDateList.observe(lifecycleOwner) {
                    Log.d("MonthVpAdpater에서 completeList-> dateRecyclerView로 보냄", "${it}")
                    this.setCompleteDateList(
                        completeDateList.value?.dayInfoList ?: listOf(),
                        start = completeDateList.value?.start ?: 0,
                        end = completeDateList.value?.end ?: 31
                    )
                }

                setPostSelectData {
                    postSelectDate?.invoke(it)
                    Log.d("please/CalendarViewPagerAdpater", "$it")
                }
            }

        }
    }

    companion object {
        //const val MAX_ITEM_COUNT = Int.MAX_VALUE
        const val MAX_ITEM_COUNT = 10
    }
}