package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.data.model.response.notice2.ResNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeDetailData
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2
import com.example.sobok_android.domain.model.notice.NoticeListData
import com.example.sobok_android.domain.model.notice2.NoticeDetailData
import com.example.sobok_android.domain.model.notice2.NoticeGenericData
import com.example.sobok_android.domain.model.notice2.NoticeListData2

object NoticeMapper {

    // 앱잼
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

    // 릴리즈1.0
    fun mapperToNoticeListData2(resNoticeListData2: ResNoticeListData2) : NoticeListData2 {
        return NoticeListData2(
            data = NoticeListData2.Data(
                infoList = resNoticeListData2.data.infoList.map {
                    NoticeListData2.Data.Info(it.noticeId, it.section, it.isOkay, it.isSend, it.createdAt, it.senderName, it.pillName, it.pillId, it.senderGroupId)
                },
                username = resNoticeListData2.data.username
            )
        )
    }

    fun mapperToNoticeDetailData(resNoticeDetailData: ResNoticeDetailData) : NoticeDetailData {
        return NoticeDetailData(
            data = NoticeDetailData.Data(
                pillName = resNoticeDetailData.data.pillName,
                takeInterval = resNoticeDetailData.data.takeInterval,
                scheduleTime = resNoticeDetailData.data.scheduleTime,
                startDate = resNoticeDetailData.data.startDate,
                endDate = resNoticeDetailData.data.endDate,
                scheduleDay = resNoticeDetailData.data.scheduleDay,
                scheduleSpecific = resNoticeDetailData.data.scheduleSpecific
            )
        )
    }

}