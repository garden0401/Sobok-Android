package com.example.sobok_android.presentation.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.sobok_android.R
import com.example.sobok_android.databinding.LayoutCalendarTopBinding
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.presentation.view.calendar.adapter.CalendarMonthViewPagerAdapter
import com.example.sobok_android.presentation.view.calendar.adapter.CalendarMonthViewPagerAdapter.Companion.MAX_ITEM_COUNT
import com.example.sobok_android.presentation.view.calendar.adapter.CalendarWeekViewPagerAdapter
import com.example.sobok_android.util.DateTimeUtil
import com.example.sobok_android.util.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class CalendarView(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {


    private val _sendDate = MutableLiveData<Calendar>()
    val sendDate: LiveData<Calendar>
        get() = _sendDate

    //이게 뭐지?
    private fun setAdapterData(data : CalendarDayListData) {
        if(_isMonth.value!!) {
            Log.d("month가 true-> mothViewPagerAdpater로", "${data}")
            calendarMonthViewPagerAdapter.setCompleteDateList(data)
        }else {
            //calendarWeekViewPagerAdapter.setCompleteDateList(data)
        }
    }

    private val completeDateList = MutableLiveData<CalendarDayListData>()

    fun setCompleteDateList(value: CalendarDayListData) {
        completeDateList.postValue(value)
    }

    private val _selectDate = MutableLiveData<String>()
    val selectDate: LiveData<String>
        get() = _selectDate

    private var postSelectDate: ((String) -> Unit)? = null
    fun setPostSelectDate(value: (String) -> Unit) {
        postSelectDate = value
    }

    var sendCalendar = MutableLiveData<Calendar>()
    private var sendCalendarGetter : ((Calendar) -> Unit)? = null
    fun setSendCalendar(value: (Calendar) -> Unit) {
        sendCalendarGetter = value
    }

    private var _isMonth = MutableLiveData(true)
    fun setIsMonth(value: Boolean) {
        _isMonth.value = value
    }

    private val calendarTopView
        get() = createCalendarTopView()
    lateinit var layoutCalendarTopBinding: LayoutCalendarTopBinding
    private lateinit var calendarMonthViewPagerAdapter: CalendarMonthViewPagerAdapter
    private lateinit var calendarWeekViewPagerAdapter: CalendarWeekViewPagerAdapter
    private lateinit var calendarViewPager: ViewPager2

    private val fragmentViewLifecycleOwner
        get() = findFragment<Fragment>().viewLifecycleOwner

    init {
        orientation = VERTICAL
        addView(calendarTopView)
    }

    private fun createCalendarTopView(): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        layoutCalendarTopBinding =
            DataBindingUtil.inflate(inflater, R.layout.layout_calendar_top, this, false)

        return layoutCalendarTopBinding.root
    }

    private val calendarViewPagerGenerator = {
        ViewPager2(context, null).apply {
            layoutParams = LayoutParams(
                MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                topMargin = 4.dp
                marginStart = 8.dp
                marginEnd = 8.dp
            }

            _isMonth.observe(fragmentViewLifecycleOwner) {
                when(it) {
                    true -> {
                        adapter = calendarMonthViewPagerAdapter
                        Log.d("point/CalendarView-monthAdapter", "연결")
                        calendarMonthViewPagerAdapter.setPostSelectData {
                            _selectDate.postValue(it)

                        }
                        //calendarMonthViewPagerAdapter
                        calendarMonthViewPagerAdapter.setSendDateGetter {
                            Log.d("sleepy///Month에서 넘어온 sendDate!!! -> 이게 observe로 감", "${it.time}")
                            _sendDate.postValue(it)
                        }
                        calendarMonthViewPagerAdapter.setPostSendCalendar {
                            sendCalendar.value = it
                        }
                        calendarMonthViewPagerAdapter.setCurrentPostion(FIRST_POSITION)
                        setCurrentItem(FIRST_POSITION, false)
                    }else -> {
                        adapter = calendarWeekViewPagerAdapter
                        Log.d("point/CalendarView-weekAdapter", "연결")
                        calendarWeekViewPagerAdapter.setPostSelectData {
                            _selectDate.postValue(it)
                        }
                        setCurrentItem(FIRST_POSITION, false)
                    }
                }
            }
        }
    }

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private lateinit var lazyPagerAddJob: Job
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lazyPagerAddJob = scope.launch {
            _isMonth.observe(fragmentViewLifecycleOwner) {
                when(it) {
                    true -> {
                        calendarMonthViewPagerAdapter = CalendarMonthViewPagerAdapter(fragmentViewLifecycleOwner)
                        calendarMonthViewPagerAdapter.setIsMonth(_isMonth.value ?: true)

                    }
                    else -> {
                        calendarWeekViewPagerAdapter = CalendarWeekViewPagerAdapter(fragmentViewLifecycleOwner)
                        calendarWeekViewPagerAdapter.setIsMonth(_isMonth.value ?: true)
                    }
                }
            }
            calendarViewPager = calendarViewPagerGenerator()
            calendarViewPager.registerOnPageChangeCallback(
                object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        Log.d("onpageSelected", "${position}")
                        //calendarMonthViewPagerAdapter.notifyItemChanged(position)
                        calendarMonthViewPagerAdapter.setCurrentPostion(position)
                    }
                }
            )
            TransitionManager.beginDelayedTransition(this@CalendarView)
            addView(calendarViewPager)
            Log.d("currentItem!!!!!!!!!!1", "${calendarViewPager.currentItem}")
            completeDateList.observe(fragmentViewLifecycleOwner) {
                Log.d("want/calendarView-completeDateList", "${it}")
                setAdapterData(it)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lazyPagerAddJob.cancel()
    }

    companion object {
        const val FIRST_POSITION = MAX_ITEM_COUNT / 2
    }

}