package com.example.sobok_android.data.repository.calendar

import com.example.sobok_android.data.datasource.calendar.CalendarDataSource
import com.example.sobok_android.data.mapper.CalendarMapper
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.model.home.HomePillListData
import com.example.sobok_android.domain.model.home.PillListData
import com.example.sobok_android.domain.repository.calendar.CalendarRepository

class CalendarRepositoryImpl(private val calendarDataSource: CalendarDataSource) :
    CalendarRepository {
    override suspend fun getCalendarList(date: String): CalendarData {
        return CalendarMapper.mapperToCalendarData(calendarDataSource.getCalendarList(date))
    }

    override suspend fun getHomePillListData(date: String): PillListData {
        return CalendarMapper.mapperToHomePillListDat(calendarDataSource.getHomePillList(date))
    }

    override suspend fun getSharePillList(memberId: Int, date: String): PillListData {
        return CalendarMapper.mapperToSharePillListDat(calendarDataSource.getSharePillList(memberId, date))
    }
}