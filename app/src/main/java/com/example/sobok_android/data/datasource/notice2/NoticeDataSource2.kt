package com.example.sobok_android.data.datasource.notice2

import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeDetailData
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2
import retrofit2.http.Body

interface NoticeDataSource2 {
    suspend fun getNoticeList(): ResNoticeListData2

    suspend fun putNoticeCalendarShare(sendGroupId: Int, isOkay: ReqNoticeCalendarShareData): ResNoticeCalendarShareData

    suspend fun getNoticeDetail(noticeId: Int, pillId: Int): ResNoticeDetailData

}