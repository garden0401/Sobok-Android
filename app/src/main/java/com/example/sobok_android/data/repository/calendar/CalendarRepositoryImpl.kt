package com.example.sobok_android.data.repository.calendar

import com.example.sobok_android.data.datasource.calendar.CalendarDataSource
import com.example.sobok_android.data.mapper.CalendarMapper
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.repository.calendar.CalendarRepository

class CalendarRepositoryImpl(private val calendarDataSource: CalendarDataSource) :
    CalendarRepository {
    override suspend fun getCalendarList(date: String): CalendarData {
        return CalendarMapper.mapperToCalendarData(calendarDataSource.getCalendarList(date))
    }
}