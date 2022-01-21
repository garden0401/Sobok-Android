package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.data.model.response.home.ResHomePillListData
import com.example.sobok_android.data.model.response.home.ResSharePillListData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CalendarService {
    @GET("schedule/calendar")
    suspend fun getCalendar(
        @Query("date") date: String,
    ): ResCalendarData

    @GET("schedule/detail")
    suspend fun getHomePillList(
        @Query("date") date: String
    ): ResHomePillListData

    @GET("schedule/{memberId}/detail")
    suspend fun getSharePillList(
        @Path("memberId") memberId : Int,
        @Query("date") date: String
    ): ResSharePillListData
}