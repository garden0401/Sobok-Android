package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.model.request.login.ReqSignInSuccessData
import com.example.sobok_android.data.model.response.login.ResSignInSuccessData

interface SignInDataSource {

    suspend fun postSignIn(body: ReqSignInSuccessData): ResSignInSuccessData
}