package com.example.sobok_android.domain.repository.login.request

import com.example.sobok_android.data.model.request.login.ReqSignUpSuccessData
import com.example.sobok_android.domain.model.login.request.SignUpSuccessData

interface SignUpRepository {
    suspend fun postSignUpResult(body: ReqSignUpSuccessData): SignUpSuccessData
}