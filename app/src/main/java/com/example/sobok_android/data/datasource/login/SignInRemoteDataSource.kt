package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.api.LoginService
import com.example.sobok_android.data.model.request.login.ReqSignInSuccessData
import com.example.sobok_android.data.model.response.login.ResSignInSuccessData

class SignInRemoteDataSource(private val loginService: LoginService) : SignInDataSource {
    override suspend fun postSignIn(body: ReqSignInSuccessData): ResSignInSuccessData {
        return loginService.postSignIn(body)
    }
}
