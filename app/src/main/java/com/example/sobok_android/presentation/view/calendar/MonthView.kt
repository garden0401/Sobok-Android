package com.example.sobok_android.presentation.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.databinding.ItemCalendarDateBinding
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.presentation.view.calendar.adapter.CalendarDateRecyclerViewAdapter
import java.text.SimpleDateFormat
import java.util.*

class MonthView(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    private var dateItemGetter: (() -> List<ResCalendarData.CalendarDate>)? = null
    private var calendarGetter: (() -> Calendar)? = null
    private var weekCalendarGetter: (() -> Calendar)? = null
    private val nowCalendar = Calendar.getInstance(Locale.KOREA) //오늘 날짜
    private var calendarDateRecyclerViewAdapter: (() -> CalendarDateRecyclerViewAdapter)? = null

    private val dateItems = (1..42).map { _ ->
        ItemCalendarDateBinding.inflate(LayoutInflater.from(context), null, false).apply {
            root.id = ViewCompat.generateViewId()
        }
    }

    private var completeDateList: List<CalendarData.CalendarDate?>? = null

    fun setCompleteDateGetter(data : List<CalendarData.CalendarDate?>) {
        completeDateList = data
    }

    fun setDateItemGetter(listener: () -> List<ResCalendarData.CalendarDate>) {
        dateItemGetter = listener
    }

    fun setMonthlyAdapter(listener: () -> CalendarDateRecyclerViewAdapter) {
        calendarDateRecyclerViewAdapter = listener
    }

    private val outerConstraintLayout = ConstraintLayout(context).apply {
        id = ViewCompat.generateViewId()

        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    val dateRecyclerView = RecyclerView(context, attrs).apply {
        id = ViewCompat.generateViewId()

        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutManager = GridLayoutManager(context, 7, GridLayoutManager.VERTICAL, false)
        overScrollMode = OVER_SCROLL_NEVER
    }

    init {
        addView(outerConstraintLayout)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        outerConstraintLayout.removeAllViews()
        outerConstraintLayout.addView(dateRecyclerView)
//        dateRecyclerView.adapter = CalendarDateRecyclerViewAdapter().apply {
//            isMonth = true // 일단 넣기
//                Log.d("want//completeList-> dateRecyclerView로 보냄", "${completeDateList}")
//                this.setCompleteDateList(completeDateList)
//                setMonthCalendar(Calendar.getInstance(Locale.KOREA).apply {
//                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                    add(Calendar.MONTH, position - FIRST_POSITION) // 좌우 스와이프 시 월 +-1 계산
//                })
//                Log.d("point/dateRecyclerViewMonth", "$_isMonth")
//                setCurrentMonthGetter { isNext, comCalendar ->
//                    setMonthCalendar(
//                        comCalendar.apply {
//                            when (isNext) {
//                                true -> {
//                                    add(Calendar.MONTH, -1) // 이전 달로 만들고
//                                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                                }
//                                else -> {
//                                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                                    //add(Calendar.MONTH, ) // 좌우 스와이프 시 월 +-1 계산
//                                }
//                            }
//
//                        }
//                    )
//                }
//                setPostSelectData {
//                    postSelectDate?.invoke(it)
//                    Log.d("please/CalendarViewPagerAdpater", "$it")
//                }
//        }

//        val compCalendar = calendarGetter?.invoke()?.clone() as Calendar // 만들어진 캘린더
//        val curMonth = compCalendar.get(Calendar.MONTH) // 현재 month
//        //여기서 데이터를 넣는디????
//        repeat(42) { i ->
//            val item = dateItems[i] //아이텐 뷰
//            item.tvCalendarDate.text = compCalendar.get(Calendar.DAY_OF_MONTH).toString()
//            compCalendar.add(Calendar.DAY_OF_MONTH, 1)
//            dateRecyclerView.addView(
//                item.root, LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//            )
//        }
    }

    //calendar util
    fun Calendar.isDaySame(calendar: Calendar): Boolean {
        return get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                && get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun updateUIWithDate() {
        var pos = 0
        var index = 31
        //val dateItem = dateItemGetter?.invoke()
        //val dateItem = listOf<Date>()
        val userDateList = dateItemGetter?.invoke()
        val compCalendar = calendarGetter?.invoke()?.clone() as Calendar // 만들어진 캘린더
        val curMonth = compCalendar.get(Calendar.MONTH) // 현재 month
        val numDayOfMonth = compCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

//        monthlyAdapter = MonthlyAdapter()
//        monthlyAdapter.data = userDateList!!
        var userDateSize = 31 // ????
//        if (dateItem != null) {
//            index = dateItem.size
//        }
        var userDateItemIdx = 0

        if (userDateList != null) {
            userDateSize = userDateList.size
        }

        val sdfDay = SimpleDateFormat("dd", Locale.KOREA)

        //해당 첫번째 칸에 지난 달의 날짜를 계산
        compCalendar.set(Calendar.DAY_OF_MONTH, 1) // 1월 1일로 만듬
        if (compCalendar.get(Calendar.DAY_OF_WEEK) == 1) // 일요일이면
            compCalendar.add(Calendar.DAY_OF_MONTH, -6) // 현재 일에다가 -6을 해준다.... 11월 2x일
        else //일요일 아니야...악...
            compCalendar.add(Calendar.DAY_OF_MONTH, 2 - compCalendar.get(Calendar.DAY_OF_WEEK))

//        innerLinearLayouts.forEach {
//            it.removeAllViews()
//        }
//        outerLinearLayout.removeAllViews()

        outerConstraintLayout.removeAllViews()

        outerConstraintLayout.addView(dateRecyclerView)

        repeat(42) { i ->
            val item = dateItems[i] //아이텐 뷰
            Log.d("check/itemView", "$i")
            if (compCalendar.get(Calendar.MONTH) != curMonth) { // 현재 월이 아니면
                Log.d("check/notCurMonth", "현재 월 아님!!")
                item.isEmpty = true
            } else { // 현재 월이면
                item.isEmpty = false
                Log.d("check/curMonth", "현재 월 맞음")
                if (compCalendar.isDaySame(nowCalendar)) { // 오늘이면
                    item.tvCalendarDate.setTextColor(resources.getColor(R.color.mint_main)) // 수정해야 함
                    Log.d("check/isToday", "오늘 맞음!")
                } else { //오늘이 아니면
                    Log.d("check/notToday", "오늘 아님!")
                    if (userDateSize != 0) {
                        if (sdfDay.parse(userDateList?.get(userDateItemIdx)!!.scheduleDate.toString())
                                .toString() ==
                            compCalendar.get(Calendar.DAY_OF_MONTH).toString()
                        ) {
                            item.tvCalendarDate.setTextColor(resources.getColor(R.color.dark_mint_main))

                        } else {
                            // 아마 할거 없을 듯....
                        }
                    }

                }
            }
            item.tvCalendarDate.text = compCalendar.get(Calendar.DAY_OF_MONTH).toString()
            compCalendar.add(Calendar.DAY_OF_MONTH, 1)
            dateRecyclerView.addView(
                item.root, LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

}


//recyclerview -> gridlayoutmanager -> 42개로 만들기

