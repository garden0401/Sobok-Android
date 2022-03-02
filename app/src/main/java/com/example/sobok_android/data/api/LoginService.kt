package com.example.sobok_android.data.api

import com.example.sobok_android.data.model.request.login.ReqSignInSuccessData
import com.example.sobok_android.data.model.request.login.ReqSignUpSuccessData
import com.example.sobok_android.data.model.response.login.ResSignInSuccessData
import com.example.sobok_android.data.model.response.login.ResSignUpSuccessData
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    //POST SignIn
    @POST("auth/login/email")
    suspend fun postSignIn(
        @Body body: ReqSignInSuccessData
    ): ResSignInSuccessData

    //POST SignUp
    @POST("auth")
    suspend fun postSignUp(
        @Body body: ReqSignUpSuccessData
    ): ResSignUpSuccessData

}