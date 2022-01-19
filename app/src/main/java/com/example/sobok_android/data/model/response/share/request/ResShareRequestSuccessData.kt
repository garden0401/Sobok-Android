package com.example.sobok_android.data.model.response.share.request


import com.google.gson.annotations.SerializedName

data class ResShareRequestSuccessData(
    @SerializedName("data")
    val data: MemberInfo,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class MemberInfo(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("isOkay")
        val isOkay: Boolean?,
        @SerializedName("isSend")
        val isSend: Boolean,
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberName")
        val memberName: String,
        @SerializedName("sendGroupId")
        val sendGroupId: Int,
        @SerializedName("senderId")
        val senderId: Int,
        @SerializedName("senderName")
        val senderName: String
    )
}