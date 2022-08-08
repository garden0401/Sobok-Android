package com.example.sobok_android.data.datasource.notice2

import com.example.sobok_android.data.api.NoticeService
import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeDetailData
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2

class NoticeRemoteDataSource2(private val noticeService: NoticeService) : NoticeDataSource2 {
    override suspend fun getNoticeList(): ResNoticeListData2 {
        return noticeService.getNoticeList()
    }

    override suspend fun putNoticeCalendarShare(sendGroupId: Int, isOkay: ReqNoticeCalendarShareData): ResNoticeCalendarShareData {
        return noticeService.putNoticeCalendarShare(sendGroupId, isOkay)
    }

    override suspend fun putNoticePillAccept(pillId: Int, isOkay: ReqNoticeCalendarShareData): ResNoticeCalendarShareData {
        return noticeService.putNoticePillAccept(pillId, isOkay)
    }

    override suspend fun getNoticeDetail(noticeId: Int, pillId: Int): ResNoticeDetailData {
        return noticeService.getNoticeDetail(noticeId, pillId)
    }
}