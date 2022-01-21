package com.example.sobok_android.presentation.view.calendar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.databinding.ItemCalendarDateBinding
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.presentation.view.calendar.CalendarDayListData
import com.example.sobok_android.util.DateTimeUtil
import java.util.*

class CalendarDateRecyclerViewAdapter : RecyclerView.Adapter<CalendarDateRecyclerViewAdapter.MonthlyViewHolder>() {

    private var isSetCalenar : Boolean = false

    private var newSelectedPosition : Int = 10 // 오늘날짜로 바꿔야 함
    private var oldSelectedPosition: Int = 10

    private var _isNext: Boolean = false
    private var currentMonthGetter : ((Boolean, Calendar) -> Unit)? = null
    fun setCurrentMonthGetter(listener: (Boolean, Calendar) -> Unit) {
        currentMonthGetter = listener
    }

    private var completeDateList = listOf<CalendarDayListData.DayInfo>()
    private var start : Int = 0
    private var end : Int = 31

    fun setCompleteDateList(value: List<CalendarDayListData.DayInfo>, start: Int ,end: Int) {
        this.start = start
        this.end = end
        completeDateList = value
        notifyDataSetChanged()
    }

    private var _isMonth = true
    var isMonth: Boolean = _isMonth
        set(value) {
            Log.d("point/isMonthSetting!!!!!!!!!!!!1", "${value}")
            _isMonth = value
            field = value
        }

    private var postSelectDate: ((String) -> Unit)? = null

    fun setPostSelectData(listener: (String) -> Unit) {
        postSelectDate = listener
    }

    private var _comCalendar: Calendar = Calendar.getInstance(Locale.KOREA)
    var comCalendar: Calendar = _comCalendar

    private var _calculCalendar :Calendar = Calendar.getInstance(Locale.KOREA)

    fun setMonthCalendar(value: Calendar) {
        Log.d("point/setMonthCalendar밖에서 1일로 만들어 줌!!!!!!-month", "${value.time}")
        _comCalendar = value.clone() as Calendar //TODO clone 추가
        _comCalendar.set(Calendar.DAY_OF_MONTH, 1) // 1월 1일로 만듬
        curMonth = _comCalendar.get(Calendar.MONTH)
        _isNext = false
        Log.d("check/맨 처음 날짜", "${_comCalendar.time}")
        if (_comCalendar.get(Calendar.DAY_OF_WEEK) == 1) // 일요일이면
            _comCalendar.add(Calendar.DAY_OF_MONTH, -6)
        else
            _comCalendar.add(Calendar.DAY_OF_MONTH, 2 - _comCalendar.get(Calendar.DAY_OF_WEEK))

        isSetCalenar = true
    }

    fun setWeekCalendar(value: Calendar) {
        _comCalendar = value
        if (_comCalendar.get(Calendar.DAY_OF_WEEK) == 1) // 일요일이면
            _comCalendar.add(Calendar.DAY_OF_MONTH, -6)
        else
            _comCalendar.add(Calendar.DAY_OF_MONTH, 2 - _comCalendar.get(Calendar.DAY_OF_WEEK))

        isSetCalenar = true
    }

    private val nowCalendar = Calendar.getInstance(Locale.KOREA) //오늘 날짜
    var curMonth = comCalendar.get(Calendar.MONTH) // 현재 month

    private var calMonth : String = "1"
    fun setCurCalendarInfo(today: Date) {

        this.calYear = calYear
        this.calMonth = calMonth
        this.calDay = calDay
    }
    private var calYear : String = "2022"
    private var calDay : String = "21"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyViewHolder {
        val binding: ItemCalendarDateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_calendar_date, parent, false
        )
        return MonthlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonthlyViewHolder, position: Int) {
        holder.onBind(completeDateList[position], position)
    }

    override fun getItemCount(): Int = completeDateList.size

    inner class MonthlyViewHolder(
        private val binding: ItemCalendarDateBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(item: CalendarDayListData.DayInfo, position: Int) {
            if(position == oldSelectedPosition) {
                binding.tvCalendarDate.isSelected = false
                binding.ivCalendarDateCircle.isSelected = false
                binding.isEmpty = false
                binding.isNone = false
                binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDefaultTextStyle)

                //TODO: 오늘 날짜 저장하기, 오늘날짜 클릭리스너 달기!, 선택한 날짜 보내기
                if(completeDateList.size > oldSelectedPosition) {
                    if (completeDateList[oldSelectedPosition].isComplete == "done") {
                        binding.isComplete = true
                        binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneTextStyle)
                        binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_done_not_select)
                    } else if (completeDateList[oldSelectedPosition].isComplete == "doing") {
                        binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingTextStyle)
                        binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_doing_not_select)
                    }else {

                    }
                }
                oldSelectedPosition = newSelectedPosition
            } else {

            }

            if(position < start) {
                binding.isEmpty = true
                binding.isToday = false
                if(_comCalendar.get(Calendar.MONTH) > curMonth) {
                    _isNext = true
                }
            }else if(position > end) {

            }else {
                binding.isEmpty = false
                binding.tvCalendarDate.text = item.day

                binding.date = item.day

                if(item.day == "20") { // 오늘이면
                    binding.isNone = true
                    binding.isToday = true
                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarTodayTextStyle)

                }else {
                    binding.isToday = false
                        when (item.isComplete) {
                            "done" -> {
                                binding.isComplete = true
                                binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneTextStyle)
                            }
                            "doing" -> {
                            }
                            else -> {
                                binding.isNone = true
                            }
                        }
                }

                binding.clCalendarDate.setOnClickListener { // none일 때만!!!
                    when (binding.tvCalendarDate.isSelected) {
                        true -> {
                        }
                        else -> {
                            binding.tvCalendarDate.isSelected = true
                            binding.ivCalendarDateCircle.isSelected = true

                            newSelectedPosition = position
                            notifyItemChanged(oldSelectedPosition)

                            postSelectDate?.invoke(binding.date
                                ?: DateTimeUtil.convertCalendarToSimpleDateDetailFormat(Calendar.getInstance(Locale.KOREA))
                            )

                            if (binding.isNone == true) {
                                binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateNoneSelectTextStyle)
                            } else {
                                    if (item.isComplete == "done") {
                                        binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneSelectTextStyle)
                                        binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_done_select)
                                    } else if (item.isComplete == "doing") {
                                        binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingSelectTextStyle)
                                        binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_doing_select)
                                    }
                            }
                        }
                    }

                    //TODO: 고차함수 만들고 호출하고 외부에서 setMonth를 다시 한다...! 1월 1일을 다시 보냄..!!
                    currentMonthGetter?.invoke(_isNext, _comCalendar)

                }
            }

        }
    }
}
