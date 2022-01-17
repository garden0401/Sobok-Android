package com.example.sobok_android.data.datasource.share.request

import com.example.sobok_android.data.model.response.share.request.ResSearchResultData

class ShareRequestLocalDataSource : ShareRequestDataSource{
    override suspend fun getSearchResult() : ResSearchResultData {
        return ResSearchResultData(
            data = listOf(ResSearchResultData.Member(1, "빵빵"),
                ResSearchResultData.Member(2, "뿡뿡")),
            status = 400,
            message = "성공",
            success = true
        )
    }
}