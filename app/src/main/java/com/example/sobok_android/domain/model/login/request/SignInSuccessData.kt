package com.example.sobok_android.domain.model.login.request

import com.example.sobok_android.data.model.response.login.request.ResSignInSuccessData
import com.google.gson.annotations.SerializedName

data class SignInSuccessData(
    val data: MemberInfo
) {
    data class MemberInfo(
        @SerializedName("id")
        val id: Int,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("accesstoken")
        val accesstoken: String
        )
}