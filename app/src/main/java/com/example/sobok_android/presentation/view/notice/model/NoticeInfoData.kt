package com.example.sobok_android.presentation.view.notice.model

data class NoticeInfoData(
    val isCalendar: Boolean,
    val senderId: Int,
    val receiverId: Int,
    val createdAt: String,
    val senderName: String,
    val receiverName: String
)
