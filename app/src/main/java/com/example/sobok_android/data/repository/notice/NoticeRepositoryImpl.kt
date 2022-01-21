package com.example.sobok_android.data.repository.notice

import com.example.sobok_android.data.datasource.notice.NoticeDataSource
import com.example.sobok_android.data.mapper.NoticeMapper
import com.example.sobok_android.domain.model.notice.NoticeListData
import com.example.sobok_android.domain.repository.notice.NoticeRepository

class NoticeRepositoryImpl(private val noticeDataSource : NoticeDataSource) : NoticeRepository {
    override suspend fun getNoticeList(): NoticeListData {
        return NoticeMapper.mapperToNoticeListData(noticeDataSource.getNoticeList())
    }

}