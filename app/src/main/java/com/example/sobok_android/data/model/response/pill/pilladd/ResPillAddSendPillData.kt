package com.example.sobok_android.data.model.response.pill.pilladd

import com.google.gson.annotations.SerializedName

data class ResPillAddSendPillData(
    @SerializedName("data")
    val data: UserData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class UserData(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("receiverId")
        val receiverId: Int,
        @SerializedName("receiverName")
        val receiverName: String,
        @SerializedName("sendPillInfo")
        val sendPillInfo: List<SendPillInfo>,
        @SerializedName("senderId")
        val senderId: Int,
        @SerializedName("senderName")
        val senderName: String
    ) {
        data class SendPillInfo(
            @SerializedName("isOkay")
            val isOkay: Boolean?,
            @SerializedName("isSend")
            val isSend: Boolean,
            @SerializedName("pillId")
            val pillId: Int,
            @SerializedName("sendPillId")
            val sendPillId: Int
        )
    }
}