package com.example.sobok_android.data.datasource.share.request

import com.example.sobok_android.data.model.response.share.request.ResSearchResultData

interface ShareRequestDataSource {
    suspend fun getSearchResult() : ResSearchResultData
}