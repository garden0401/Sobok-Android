package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.model.request.login.ReqSignUpSuccessData
import com.example.sobok_android.data.model.response.login.ResSignUpSuccessData

interface SignUpDataSource {
    suspend fun postSignUp(body: ReqSignUpSuccessData): ResSignUpSuccessData
}