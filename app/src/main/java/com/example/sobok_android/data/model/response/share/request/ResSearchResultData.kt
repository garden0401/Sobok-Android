package com.example.sobok_android.data.model.response.share.request

import com.google.gson.annotations.SerializedName

data class ResSearchResultData(
    @SerializedName("data")
    val data: List<Member>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Member(
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberName")
        val memberName: String
    )
}