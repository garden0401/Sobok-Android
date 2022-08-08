package com.example.sobok_android.domain.model.notice2

import com.google.gson.annotations.SerializedName

data class NoticeListData2(
    val data: Data
    ) {
    data class Data(
        @SerializedName("username")
        val username: String,
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
            val pillId: Int? = null,
            @SerializedName("senderGroupId")
            val senderGroupId: Int? = null
        )
    }
}
