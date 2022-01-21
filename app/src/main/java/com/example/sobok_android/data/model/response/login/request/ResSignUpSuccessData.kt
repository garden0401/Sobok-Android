package com.example.sobok_android.data.model.response.login.request


import com.google.gson.annotations.SerializedName

data class ResSignUpSuccessData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: MemberAll
) {
    data class MemberAll(
        @SerializedName("user")
        val user: MemberInfo,
        @SerializedName("accesstoken")
        val accesstoken: String
    ) {
        data class MemberInfo(
            @SerializedName("id")
            val id: Int,
            @SerializedName("username")
            val username: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("idFirebase")
            val idFirebase: String,
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("updatedAt")
            val updatedAt: String
        )
    }
}