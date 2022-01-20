package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarService {
    @GET("schedule/calendar")
    suspend fun getCalendar(
        @Query("date") date: String,
    ): ResCalendarData
}