package com.example.sobok_android.data.model.response.myinfo

data class ResMyPillData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<MyPillInfoData>
) {
    data class MyPillInfoData(
        val id: Int,
        val color: String,
        val pillName: String
    )
}

