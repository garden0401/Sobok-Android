package com.example.sobok_android.data.datasource.share.request

import com.example.sobok_android.data.model.request.share.ReqShareRequestData
import com.example.sobok_android.data.model.response.share.ResGroupData
import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.data.model.response.share.request.ResShareRequestSuccessData

interface ShareRequestDataSource {
    suspend fun getSearchResult(userName: String) : ResSearchResultData

    suspend fun postSearchResult(memberId: Int, memberName: ReqShareRequestData): ResShareRequestSuccessData

    suspend fun getGroupData(): ResGroupData
}