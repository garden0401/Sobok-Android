package com.example.sobok_android.data.datasource.calendar

import com.example.sobok_android.data.api.CalendarService
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.data.model.response.home.ResHomePillListData
import com.example.sobok_android.data.model.response.home.ResSharePillListData

class CalendarRemoteDataSource(private val calendarService: CalendarService) : CalendarDataSource {
    override suspend fun getCalendarList(date: String): ResCalendarData {
        return calendarService.getCalendar(date)
    }

    override suspend fun getHomePillList(date: String): ResHomePillListData {
        return calendarService.getHomePillList(date)
    }

    override suspend fun getSharePillList(memberId: Int, date: String): ResSharePillListData {
        return calendarService.getSharePillList(memberId, date)
    }


}