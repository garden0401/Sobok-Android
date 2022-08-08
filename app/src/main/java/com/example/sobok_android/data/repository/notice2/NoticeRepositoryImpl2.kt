package com.example.sobok_android.data.repository.notice2

import com.example.sobok_android.data.datasource.notice2.NoticeDataSource2
import com.example.sobok_android.data.mapper.NoticeMapper
import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.domain.model.notice2.NoticeDetailData
import com.example.sobok_android.domain.model.notice2.NoticeGenericData
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.domain.repository.notice2.NoticeRepository2

class NoticeRepositoryImpl2(private val noticeDataSource2: NoticeDataSource2) : NoticeRepository2 {
    override suspend fun getNoticeList(): NoticeListData2 {
        return NoticeMapper.mapperToNoticeListData2(noticeDataSource2.getNoticeList())
    }

    override suspend fun putNoticeCalendarShare(sendGroupId: Int, isOkay: ReqNoticeCalendarShareData): NoticeGenericData {
        return with(noticeDataSource2.putNoticeCalendarShare(sendGroupId, isOkay)) {
            NoticeGenericData(
                this.status,
                this.success,
                this.message
            )
        }
    }

    override suspend fun getNoticeDetail(noticeId: Int, pillId: Int): NoticeDetailData {
        return NoticeMapper.mapperToNoticeDetailData(noticeDataSource2.getNoticeDetail(noticeId, pillId))
    }
}