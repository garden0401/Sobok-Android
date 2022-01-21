package com.example.sobok_android.domain.repository.login.request

import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.request.ReqSignUpSuccessData
import com.example.sobok_android.domain.model.login.request.SignInSuccessData
import com.example.sobok_android.domain.model.login.request.SignUpSuccessData

interface SignUpRepository {
    suspend fun postSignUpResult(body: ReqSignUpSuccessData): SignUpSuccessData
}