package com.example.sobok_android.data.datasource.calendar

import com.example.sobok_android.data.model.response.calendar.ResCalendarData

interface CalendarDataSource {
    suspend fun getCalendarList(date: String) : ResCalendarData
}