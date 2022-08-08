package com.example.sobok_android.domain.model.notice2

import com.example.sobok_android.data.model.response.notice2.ResNoticeCalendarShareData
import com.google.gson.annotations.SerializedName

data class NoticeGenericData(
    val status: Int,
    val success: Boolean,
    val message: String
)
