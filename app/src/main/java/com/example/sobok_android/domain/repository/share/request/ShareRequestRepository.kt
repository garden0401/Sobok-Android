package com.example.sobok_android.domain.repository.share.request

import com.example.sobok_android.data.model.request.share.ReqShareRequestData
import com.example.sobok_android.domain.model.share.request.GroupData
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData

interface ShareRequestRepository {
    suspend fun getSearchResult(userName: String) : SearchResultData

    suspend fun postSearchResult(memberId: Int, memberName: ReqShareRequestData): ShareRequestSuccessData

    suspend fun getGroupData(): GroupData
}