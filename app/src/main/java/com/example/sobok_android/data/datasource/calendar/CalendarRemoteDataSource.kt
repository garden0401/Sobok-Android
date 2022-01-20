package com.example.sobok_android.data.datasource.calendar

import com.example.sobok_android.data.api.CalendarService
import com.example.sobok_android.data.model.response.calendar.ResCalendarData

class CalendarRemoteDataSource(private val calendarService: CalendarService) : CalendarDataSource {
    override suspend fun getCalendarList(date: String): ResCalendarData {
        return calendarService.getCalendar(date)
    }
}