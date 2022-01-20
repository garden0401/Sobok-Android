package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignInSuccessData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    //POST SignIn
    @POST("auth/login/email")
    suspend fun postSignIn(
        @Body body: ReqSignInSuccessData
    ): ResSignInSuccessData
}