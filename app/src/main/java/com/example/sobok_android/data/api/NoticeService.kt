package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.data.model.response.notice2.ResNoticeCalendarShareData
import com.example.sobok_android.data.model.response.notice2.ResNoticeDetailData
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2
import retrofit2.http.*

interface NoticeService {
    //GET NoticeResult
    @GET("notice/list")
    suspend fun getNoticeResult(

    ): ResNoticeListData

    //GET NoticeList
    @GET("notice/list")
    suspend fun getNoticeList() : ResNoticeListData2

    //PUT NoticeCalendarShare
    @PUT("group/{sendGroupId}")
    suspend fun putNoticeCalendarShare(
        @Path("sendGroupId") sendGroupId: Int,
        @Body isOkay: ReqNoticeCalendarShareData
    ) : ResNoticeCalendarShareData

    //GET NoticeDetailPillInfo
    @GET("notice/list/{noticeId}/{pillId}")
    suspend fun getNoticeDetail(
        @Path("noticeId") noticeId: Int,
        @Path("pillId") pillId: Int
    ) : ResNoticeDetailData
}