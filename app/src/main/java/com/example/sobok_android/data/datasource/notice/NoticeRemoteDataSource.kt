package com.example.sobok_android.data.datasource.notice

import com.example.sobok_android.data.api.NoticeService
import com.example.sobok_android.data.api.ShareService
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.model.response.notice.ResNoticeListData
import com.example.sobok_android.data.model.response.share.request.ResSearchResultData

class NoticeRemoteDataSource(private val noticeService: NoticeService) : NoticeDataSource {
    override suspend fun getNoticeList(): ResNoticeListData {
        return noticeService.getNoticeResult()
    }

}

