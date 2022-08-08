package com.example.sobok_android.data.model.response.notice2


import com.google.gson.annotations.SerializedName

data class ResNoticeDetailData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
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