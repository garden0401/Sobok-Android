package com.example.sobok_android.presentation.view.calendar

data class CalendarDayListData(
    val start : Int,
    val end : Int,
    val dayInfoList: List<DayInfo>
){
    data class DayInfo(
        val day: String,
        val isComplete: String
    )
}
