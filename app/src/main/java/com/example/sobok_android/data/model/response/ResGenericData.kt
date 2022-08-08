package com.example.sobok_android.data.model.response

import com.google.gson.annotations.SerializedName

data class ResGenericData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
