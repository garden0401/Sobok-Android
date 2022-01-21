package com.example.sobok_android.data.model.response.pill.pilladd


import com.google.gson.annotations.SerializedName

data class ResReceivePillListData(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("pillData")
        val pillData: List<PillData>,
        @SerializedName("senderName")
        val senderName: String
    ) {
        data class PillData(
            @SerializedName("color")
            val color: String,
            @SerializedName("endDate")
            val endDate: String,
            @SerializedName("pillName")
            val pillName: String,
            @SerializedName("scheduleCycle")
            val scheduleCycle: String,
            @SerializedName("scheduleDay")
            val scheduleDay: String,
            @SerializedName("scheduleSpecific")
            val scheduleSpecific: Any,
            @SerializedName("scheduleTime")
            val scheduleTime: List<String>,
            @SerializedName("startDate")
            val startDate: String
        )
    }
}