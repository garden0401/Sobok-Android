package com.example.sobok_android.data.model.response.home


import com.google.gson.annotations.SerializedName

data class ResSharePillListData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Data>
) {
    data class Data(
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
            val stickerId: List<StickerId>,
            @SerializedName("stickerTotalCount")
            val stickerTotalCount: Int,
            @SerializedName("isLikedSchedule")
            val isLikedSchedule: Boolean
        ) {
            data class StickerId(
                @SerializedName("likeScheduleId")
                val likeScheduleId: Int,
                @SerializedName("stickerId")
                val stickerId: Int
            )
        }
    }
}