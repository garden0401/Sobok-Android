package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.domain.model.calendar.CalendarData

object CalendarMapper {
    fun mapperToCalendarData(resCalendarData: ResCalendarData): CalendarData {
        return CalendarData(
            data = resCalendarData.data.map {
                CalendarData.CalendarDate(isComplete = it.isComplete, scheduleDate = it.scheduleDate)
            }
        )
    }
}