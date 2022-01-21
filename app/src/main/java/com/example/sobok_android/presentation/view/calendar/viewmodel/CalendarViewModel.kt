package com.example.sobok_android.presentation.view.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.repository.calendar.CalendarRepository
import com.example.sobok_android.presentation.view.calendar.CalendarDayListData
import com.example.sobok_android.util.DateTimeUtil
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel(private val calendarRepository: CalendarRepository) : ViewModel() {
    private val _remoteDateList = MutableLiveData<CalendarData>()
    var remoteDateList: LiveData<CalendarData> = _remoteDateList

    private val _selectDate = MutableLiveData<String>()
    var selectDate: LiveData<String> = _selectDate

    fun postSelectDate(value: String) {
        _selectDate.postValue(value)
    }

    //LiveData로 변경

    private var _completeDateList = MutableLiveData<CalendarDayListData>(CalendarDayListData(0, 0, listOf()))
    var completeDateList: LiveData<CalendarDayListData> =
        _completeDateList
        get() = _completeDateList


    private var _isMonth = MutableLiveData<Boolean>(true)
    var isMonth: LiveData<Boolean> = _isMonth
        get() = _isMonth

    fun postIsMonth(value: Boolean) {
        _isMonth.value = value
    }

    private var _sendDate = MutableLiveData<Calendar>(Calendar.getInstance(Locale.KOREA))

    // Calendar로 변환
    val sendDate: LiveData<Calendar> = _sendDate
    fun postSendDate(value: Calendar) {
        _sendDate.value = value
    }

    fun getCalendarList() = viewModelScope.launch {
        kotlin.runCatching { calendarRepository.getCalendarList(requireNotNull(DateTimeUtil.convertUSDateToDashFormatString(_sendDate.value!!.time))) } //_curMonthFirstDaycalendar를 변환해서 보내기
            .onSuccess {
                _remoteDateList.postValue(it)
                Log.d("서버통신", "성공${it}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("서버통신", "실패${it.message}")
            }
    }

    var nowCalendar = initCalendar(Calendar.getInstance(Locale.KOREA)) // 그 달의 1일로

    fun initCalendar(calendar: Calendar): Calendar {
        return calendar.apply {
            set(Calendar.DAY_OF_MONTH, 1)
        }
    }

    private val _curCalendar = MutableLiveData(nowCalendar)

    private var _dynamicCalendar: Calendar = Calendar.getInstance(Locale.KOREA)


    private var start: Int = 0
    private var end: Int = 0

    fun setDynamicCalendar(value: Calendar) {
        _dynamicCalendar = value.clone() as Calendar
        end = _dynamicCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        if (_dynamicCalendar.get(Calendar.DAY_OF_WEEK) == 1) {
            _dynamicCalendar.add(Calendar.DAY_OF_MONTH, -6)
            start = 6
        } else {
            start = _dynamicCalendar.get(Calendar.DAY_OF_WEEK) - 2
            _dynamicCalendar.add(
                Calendar.DAY_OF_MONTH,
                2 - _dynamicCalendar.get(Calendar.DAY_OF_WEEK)
            )
        }
        end += (start - 1)
        calCulCalendarDateList(_dynamicCalendar)
    }

    private fun calCulCalendarDateList(dynamicCalendar: Calendar) {
        var idx = 0
        var maxIdx = 0
        if (_remoteDateList.value != null) {
            maxIdx = _remoteDateList.value!!.data.size.minus(1)
        } else {
            maxIdx = -1
        }


        val addDateList = mutableListOf<CalendarDayListData.DayInfo>()
        //val calCulCalendar: Calendar = dynamicCalendar.clone() as Calendar
        val count = when (_isMonth.value) {
            true -> 42
            else -> 7
        }

        var day = 1
        repeat(count) { // 0~41
            if (it < start) {
                addDateList.add(CalendarDayListData.DayInfo("null", "none"))
            } else if (it > end) {

            } else {
                    if (idx <= maxIdx) {
                        if (DateTimeUtil.convertDateToDayOfMonth(_remoteDateList.value!!.data[idx].scheduleDate)
                            == day
                        ) {
                            addDateList.add(
                                CalendarDayListData.DayInfo(
                                    day.toString(),
                                    remoteDateList.value!!.data[idx++].isComplete
                                )
                            )
                            ++day
                        } else {
                            addDateList.add(CalendarDayListData.DayInfo(day.toString(), "none"))
                            ++day
                        }
                    } else {
                        addDateList.add(CalendarDayListData.DayInfo(day.toString(), "none"))
                        ++day
                    }
            }
        }
        _completeDateList.value = CalendarDayListData(start, end, addDateList)
    }

}