package com.example.sobok_android.data.model.response.calendar

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResCalendarData(
    @SerializedName("data")
    val data: List<CalendarDate>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class CalendarDate(
        @SerializedName("isCheckCount")
        val isCheckCount: String,
        @SerializedName("isComplete")
        val isComplete: String,
        @SerializedName("scheduleCount")
        val scheduleCount: String,
        @SerializedName("scheduleDate")
        val scheduleDate: Date
    )
}
