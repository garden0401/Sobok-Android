package com.example.sobok_android.data.datasource.share.request

import com.example.sobok_android.data.api.ShareService
import com.example.sobok_android.data.model.response.share.ResGroupData
import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.data.model.response.share.request.ResShareRequestSuccessData

class ShareRequestRemoteDataSource(private val shareService: ShareService) : ShareRequestDataSource {
    override suspend fun getSearchResult(userName: String): ResSearchResultData {
        return shareService.getShareRequestResult(userName)
    }

    override suspend fun postSearchResult(memberId: Int, memberName: String): ResShareRequestSuccessData {
        return shareService.postShareRequest(memberId, memberName)
    }

    override suspend fun getGroupData(): ResGroupData {
        return shareService.getGroupData()
    }

}