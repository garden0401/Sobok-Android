package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.response.myinfo.ResMyPillData
import retrofit2.http.GET

interface MyInfoService {
    @GET("user/pill")
    suspend fun getMyPillList(
    ): ResMyPillData
}