package com.example.sobok_android.data.datasource.notice2

import com.example.sobok_android.data.api.NoticeService
import com.example.sobok_android.data.model.response.notice2.ResNoticeListData2

class NoticeRemoteDataSource2(private val noticeService: NoticeService) : NoticeDataSource2 {
    override suspend fun getNoticeList(): ResNoticeListData2 {
        return noticeService.getNoticeList()
    }
}