package com.example.sobok_android.data.model.request.pill.pilladd


import com.google.gson.annotations.SerializedName

data class ReqPillAddSendPillData(
    @SerializedName("pillList")
    val pillList: List<PillInfo>
) {
    data class PillInfo(
        @SerializedName("color")
        val color: String,
        @SerializedName("cycle")
        val cycle: String,
        @SerializedName("day")
        val day: String,
        @SerializedName("end")
        val end: String,
        @SerializedName("isStop")
        val isStop: String,
        @SerializedName("pillName")
        val pillName: String,
        @SerializedName("specific")
        val specific: String,
        @SerializedName("start")
        val start: String,
        @SerializedName("time")
        val time: List<String>
    )
}