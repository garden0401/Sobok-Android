package com.example.sobok_android.data.model.response.notice2


import com.google.gson.annotations.SerializedName

data class ResNoticeListData2(
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
        val infoList: List<Info>
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
            val pillName: String? = null,
            @SerializedName("pillId")
            val pillId: Int? = null
        )
    }
}