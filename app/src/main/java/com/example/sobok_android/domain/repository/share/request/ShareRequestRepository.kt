package com.example.sobok_android.domain.repository.share.request

import com.example.sobok_android.domain.model.share.request.SearchResultData

interface ShareRequestRepository {
    suspend fun getSearchResult(userName: String) : SearchResultData
}