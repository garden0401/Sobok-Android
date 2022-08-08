package com.example.sobok_android.data.model.response.notice2


import com.google.gson.annotations.SerializedName

data class ResNoticeCalendarShareData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
)