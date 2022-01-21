package com.example.sobok_android.domain.repository.calendar

import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.domain.model.home.HomePillListData
import com.example.sobok_android.domain.model.home.PillListData

interface CalendarRepository {
    suspend fun getCalendarList(date: String): CalendarData

    suspend fun getHomePillListData(date: String): PillListData

    suspend fun getSharePillList(memberId: Int, date: String): PillListData
}