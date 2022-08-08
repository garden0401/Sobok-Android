package com.example.sobok_android.data.datasource.calendar

import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.data.model.response.home.ResHomePillListData
import com.example.sobok_android.data.model.response.home.ResSharePillListData

interface CalendarDataSource {
    suspend fun getCalendarList(date: String): ResCalendarData

    suspend fun getHomePillList(date: String): ResHomePillListData

    suspend fun getSharePillList(memberId: Int, date: String): ResSharePillListData

}