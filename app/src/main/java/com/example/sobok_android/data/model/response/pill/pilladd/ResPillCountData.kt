package com.example.sobok_android.data.model.response.pill.pilladd


import com.google.gson.annotations.SerializedName

data class ResPillCountData(
    @SerializedName("data")
    val data: PillCount,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class PillCount(
        @SerializedName("pillCount")
        val pillCount: Int
    )
}