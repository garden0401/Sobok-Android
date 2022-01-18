package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.share.request.ResSearchResultData
import com.example.sobok_android.data.model.response.share.request.ResShareRequestSuccessData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShareService {
    //GET ShareRequestResult
    @GET("user")
    suspend fun getShareRequestResult(
        @Query("username") userName: String
    ): ResSearchResultData

    //POST ShareRequest
    @POST("group")
    suspend fun postShareRequest(
        @Query("memberId") memberId: Int,
        @Query("memberName") memberName: String
    ): ResShareRequestSuccessData
}