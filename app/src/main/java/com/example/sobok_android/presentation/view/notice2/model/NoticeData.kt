package com.example.sobok_android.presentation.view.notice2.model


import com.google.gson.annotations.SerializedName

data class NoticeData(
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
        @SerializedName("infoList")
        val infoList: Info
    ) {
        data class Info(
            @SerializedName("noticeId")
            val noticeId: Int,
            @SerializedName("section")
            val section: String,
            @SerializedName("isOkay")
            val isOkay: String,
            @SerializedName("isSend")
            val isSend: Boolean,
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("senderName")
            val senderName: String,
            @SerializedName("pillName")
            val pillName: String,
            @SerializedName("pillId")
            val pillId: Int
        )
    }
}