package com.example.sobok_android.domain.model.notice2

import com.example.sobok_android.data.model.response.notice2.ResNoticeDetailData
import com.google.gson.annotations.SerializedName

data class NoticeDetailData(
    val data: Data
) {
    data class Data(
        @SerializedName("pillName")
        val pillName: String,
        @SerializedName("takeInterval")
        val takeInterval: Int,
        @SerializedName("scheduleTime")
        val scheduleTime: List<String>,
        @SerializedName("startDate")
        val startDate: String,
        @SerializedName("endDate")
        val endDate: String,
        @SerializedName("scheduleDay")
        val scheduleDay: String? = null,
        @SerializedName("scheduleSpecific")
        val scheduleSpecific: String? = null
    )
}
