package com.example.sobok_android.data.datasource.login

import com.example.sobok_android.data.api.LoginService
import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.request.ReqSignUpSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignInSuccessData
import com.example.sobok_android.data.model.response.login.request.ResSignUpSuccessData

class SignUpRemoteDataSource(private val loginService: LoginService) : SignUpDataSource {
    override suspend fun postSignUp(body: ReqSignUpSuccessData): ResSignUpSuccessData {
        return loginService.postSignUp(body)
    }
}
