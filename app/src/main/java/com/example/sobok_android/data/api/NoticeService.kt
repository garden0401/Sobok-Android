package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {
    //GET NoticeResult
    @GET("notice/list")
    suspend fun getNoticeResult(

    ): ResNoticeListData

    //GET NoticeList
    @GET("notice/list")
    suspend fun getNoticeList() : ResNoticeListData2
}