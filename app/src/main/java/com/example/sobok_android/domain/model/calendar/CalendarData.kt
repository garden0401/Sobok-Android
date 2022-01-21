package com.example.sobok_android.domain.model.calendar

import java.util.*

data class CalendarData(
    val data: List<CalendarDate>,
) {
    data class CalendarDate(
        val isComplete: String,
        val scheduleDate: Date
    )
}
