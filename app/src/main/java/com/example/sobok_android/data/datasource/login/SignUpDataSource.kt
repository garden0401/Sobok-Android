package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.request.ReqSignUpSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignInSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignUpSuccessData

interface SignUpDataSource {
    suspend fun postSignUp(body: ReqSignUpSuccessData): ResSignUpSuccessData
}