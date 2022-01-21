package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignInSuccessData

interface SignInDataSource {

    suspend fun postSignIn(body: ReqSignInSuccessData): ResSignInSuccessData
}