package com.example.sobok_android.data.model.response.share.request

import com.google.gson.annotations.SerializedName

data class ResSearchResultData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<Member>
) {
    data class Member(
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberName")
        val memberName: String
    )
}