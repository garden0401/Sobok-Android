package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.domain.model.notice.NoticeListData

object NoticeMapper {
    fun mapperToNoticeListData(resNoticeListData: ResNoticeListData) : NoticeListData {
        return NoticeListData(
            data = NoticeListData.NoticeListDataDetail(
                pillInfo = resNoticeListData.data.pillInfo.map {
                NoticeListData.NoticeListDataDetail.PillInfoData(it.senderId, it.receiverId, it.createdAt, it.senderName, it.receiverName)
            },
                calendarInfo = resNoticeListData.data.calendarInfo.map {
                    NoticeListData.NoticeListDataDetail.CalendarInfoData(it.userId, it.username, it.createdAt)
                }
            )
        )
    }
}