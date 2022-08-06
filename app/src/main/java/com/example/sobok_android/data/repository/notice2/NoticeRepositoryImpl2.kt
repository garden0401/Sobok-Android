package com.example.sobok_android.data.repository.notice2

import com.example.sobok_android.data.datasource.notice2.NoticeDataSource2
import com.example.sobok_android.data.mapper.NoticeMapper
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.domain.repository.notice2.NoticeRepository2

class NoticeRepositoryImpl2(private val noticeDataSource2: NoticeDataSource2) : NoticeRepository2 {
    override suspend fun getNoticeList(): NoticeListData2 {
        return NoticeMapper.mapperToNoticeListData2(noticeDataSource2.getNoticeList())
    }
}