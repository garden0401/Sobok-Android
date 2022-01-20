package com.example.sobok_android.domain.repository.calendar

import com.example.sobok_android.domain.model.calendar.CalendarData

interface CalendarRepository {
    suspend fun getCalendarList(date: String) : CalendarData
}