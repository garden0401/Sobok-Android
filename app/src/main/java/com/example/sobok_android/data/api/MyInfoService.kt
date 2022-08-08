package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.request.myinfo.ReqMyNicknameData
import com.example.sobok_android.data.model.response.ResGenericData
import com.example.sobok_android.data.model.response.myinfo.ResMyPillData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface MyInfoService {
    @GET("user/pill")
    suspend fun getMyPillList(
    ): ResMyPillData

    @POST("user/name")
    suspend fun postUserName(
        @Body username: ReqMyNicknameData
    ): ResGenericData

    @PUT("user/nickname")
    suspend fun putUserName(
        @Body username: ReqMyNicknameData
    ): ResGenericData
}