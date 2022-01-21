package com.example.sobok_android.data.repository.login.request

import com.example.sobok_android.data.datasource.login.SignInDataSource
import com.example.sobok_android.data.datasource.login.SignUpDataSource
import com.example.sobok_android.data.mapper.SignInMapper
import com.example.sobok_android.data.mapper.SignUpMapper
import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.data.model.request.ReqSignUpSuccessData
import com.example.sobok_android.domain.model.login.request.SignInSuccessData
import com.example.sobok_android.domain.model.login.request.SignUpSuccessData
import com.example.sobok_android.domain.repository.login.request.SignInRepository
import com.example.sobok_android.domain.repository.login.request.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun postSignUpResult(body: ReqSignUpSuccessData): SignUpSuccessData {
        return SignUpMapper.mapperToSignUpResultSuccessData(signUpDataSource.postSignUp(body))
    }
}

