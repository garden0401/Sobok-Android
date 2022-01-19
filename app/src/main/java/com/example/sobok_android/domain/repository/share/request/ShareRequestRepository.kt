package com.example.sobok_android.domain.repository.share.request

import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData

interface ShareRequestRepository {
    suspend fun getSearchResult(userName: String) : SearchResultData

    suspend fun postSearchResult(memberId: Int, memberName: String): ShareRequestSuccessData
}