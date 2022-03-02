package com.example.sobok_android.domain.repository.login.request

import com.example.sobok_android.data.model.request.login.ReqSignInSuccessData
import com.example.sobok_android.domain.model.login.request.SignInSuccessData

interface SignInRepository {
    suspend fun postSignInResult(body: ReqSignInSuccessData): SignInSuccessData
}

