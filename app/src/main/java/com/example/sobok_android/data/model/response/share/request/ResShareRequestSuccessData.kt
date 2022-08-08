package com.example.sobok_android.data.model.response.share.request


import com.google.gson.annotations.SerializedName

data class ResShareRequestSuccessData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: MemberInfo
) {
    data class MemberInfo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("memberName")
        val memberName: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("updatedAt")
        val updatedAt: String,
        @SerializedName("isSend")
        val isSend: Boolean,
        @SerializedName("isOkay")
        val isOkay: String,
        @SerializedName("noticeId")
        val noticeId: Int
    )
}