package com.example.sobok_android.domain.repository.notice2

import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.domain.model.notice2.NoticeDetailData
import com.example.sobok_android.domain.model.notice2.NoticeGenericData
import com.example.sobok_android.domain.model.notice2.NoticeListData2

interface NoticeRepository2 {
    suspend fun getNoticeList() : NoticeListData2

    suspend fun putNoticeCalendarShare(sendGroupId: Int, isOkay: ReqNoticeCalendarShareData): NoticeGenericData

    suspend fun getNoticeDetail(noticeId: Int, pillId: Int): NoticeDetailData

}