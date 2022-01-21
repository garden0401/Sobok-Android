package com.example.sobok_android.data.model.response.share


import com.google.gson.annotations.SerializedName

data class ResGroupData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<MemberInfo>
) {
    data class MemberInfo(
        @SerializedName("groupId")
        val groupId: Int,
        @SerializedName("memberId")
        val memberId: Int,
        @SerializedName("memberName")
        val memberName: String
    )
}