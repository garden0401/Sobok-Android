package com.example.sobok_android.data.repository.share.request

import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.mapper.ShareRequestMapper
import com.example.sobok_android.data.model.request.share.ReqShareRequestData
import com.example.sobok_android.domain.model.share.request.GroupData
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository

class ShareRequestRepositoryImpl(private val shareRequestDataSource: ShareRequestDataSource) : ShareRequestRepository {
    override suspend fun getSearchResult(userName: String): SearchResultData {
        return ShareRequestMapper.mapperToSearchResultData(shareRequestDataSource.getSearchResult(userName))
    }

    override suspend fun postSearchResult(memberId: Int, memberName: ReqShareRequestData): ShareRequestSuccessData {
        return ShareRequestMapper.mapperToSearchResultSuccessData(shareRequestDataSource.postSearchResult(memberId, memberName))
    }

    override suspend fun getGroupData(): GroupData {
        return ShareRequestMapper.mapperToGroupData(shareRequestDataSource.getGroupData())
    }
}