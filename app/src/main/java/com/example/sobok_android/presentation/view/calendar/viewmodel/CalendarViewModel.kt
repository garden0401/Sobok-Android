package com.example.sobok_android.presentation.view.calendar.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.repository.calendar.CalendarRepository
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

    private var _completeDateList = mutableListOf<CalendarData.CalendarDate?>()
    var completeDateList: List<CalendarData.CalendarDate?> =
        _completeDateList
    get() = _completeDateList


    private var _isMonth = MutableLiveData<Boolean>(true)
    var isMonth: LiveData<Boolean> = _isMonth
        get() = _isMonth

    fun postIsMonth(value: Boolean) {
        _isMonth.value = value
    }

    private var _sendDate= MutableLiveData<String>(DateTimeUtil.convertUSDateToDashFormatString(Calendar.getInstance(Locale.KOREA).time))
    val sendDate : LiveData<String> = _sendDate
        fun postSendDate(value: String) {
            _sendDate.value = value
        }

    fun getCalendarList() = viewModelScope.launch {
        kotlin.runCatching { calendarRepository.getCalendarList(requireNotNull(_sendDate.value)) } //_curMonthFirstDaycalendar를 변환해서 보내기
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
    val curCalendar: LiveData<Calendar>
        get() = _curCalendar

    private var curMonth: Int = Calendar.getInstance(Locale.KOREA).get(Calendar.MONTH)

    private val _curPageFirstDayCalendar =
        MutableLiveData<Calendar>(Calendar.getInstance(Locale.KOREA))
    var curPageFirstDayCalendar: LiveData<Calendar> = _curPageFirstDayCalendar
        get() = _curPageFirstDayCalendar

    fun postCurPageFirstDayCalendar(value: Calendar) {
        Log.d("want//postCurPageFirstDay", "${value.time}")
        _curPageFirstDayCalendar.postValue(value.clone() as Calendar)
        _curPageFirstDayCalendar.value?.set(Calendar.DAY_OF_MONTH, 1)
        curMonth = _curPageFirstDayCalendar.value?.get(Calendar.MONTH)!!
        setDynamicCalendar(_curPageFirstDayCalendar.value!!.clone() as Calendar)
    }

    private var _dynamicCalendar: Calendar = Calendar.getInstance(Locale.KOREA)

    private fun setDynamicCalendar(value: Calendar) {
        Log.d("@@@@22setDynamicCalendar", "${value.time}")
        _dynamicCalendar = value.clone() as Calendar
        if (_dynamicCalendar.get(Calendar.DAY_OF_WEEK) == 1)
            _dynamicCalendar.add(Calendar.DAY_OF_MONTH, -6)
        else
            _dynamicCalendar.add(
                Calendar.DAY_OF_MONTH,
                2 - _dynamicCalendar.get(Calendar.DAY_OF_WEEK)
            )
        Log.d("@@@@_dynamicCalendar이전 달 날짜", "${_dynamicCalendar.time}")
        calCulCalendarDateList(_dynamicCalendar)

    }

    private fun calCulCalendarDateList(dynamicCalendar: Calendar) {
        var idx = 0
        var maxIdx = 0
        if (_remoteDateList.value != null) {
            maxIdx = _remoteDateList.value!!.data.size.minus(1)
        } else {
            maxIdx = 0
        }

        val addDateList = mutableListOf<CalendarData.CalendarDate?>()
        val calCulCalendar: Calendar = dynamicCalendar.clone() as Calendar
        val count = when (_isMonth.value) {
            true -> 42
            else -> 7
        }

        repeat(count) {
            if (maxIdx == 0) {
                addDateList.add(null)
            } else {
                if (calCulCalendar.get(Calendar.MONTH) != curMonth) {
                    addDateList.add(null)
                } else {

                    if (idx <= maxIdx) {
                        if (DateTimeUtil.convertDateToDayOfMonth(_remoteDateList.value!!.data[idx].scheduleDate) ==
                            calCulCalendar.get(Calendar.DAY_OF_MONTH)
                        ) {
                            addDateList.add(_remoteDateList.value!!.data[idx++])
                        } else {
                            addDateList.add(null)
                        }
                    } else {
                        addDateList.add(null)
                    }
                }
            }
            calCulCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        _completeDateList = addDateList
        Log.d("want//addList", "${addDateList}")
    }
}