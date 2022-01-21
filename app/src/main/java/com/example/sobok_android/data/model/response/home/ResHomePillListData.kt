package com.example.sobok_android.data.model.response.home


import com.google.gson.annotations.SerializedName

data class ResHomePillListData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<ScheduleInfo>
) {
    data class ScheduleInfo(
        @SerializedName("scheduleTime")
        val scheduleTime: String,
        @SerializedName("scheduleList")
        val scheduleList: List<Schedule>
    ) {
        data class Schedule(
            @SerializedName("scheduleId")
            val scheduleId: Int,
            @SerializedName("pillId")
            val pillId: Int,
            @SerializedName("pillName")
            val pillName: String,
            @SerializedName("scheduleTime")
            val scheduleTime: String,
            @SerializedName("isCheck")
            val isCheck: Boolean,
            @SerializedName("color")
            val color: String,
            @SerializedName("stickerId")
            val stickerId: List<Int?>,
            @SerializedName("stickerTotalCount")
            val stickerTotalCount: Int
        )
    }
}
