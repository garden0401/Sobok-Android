package com.example.sobok_android.data.repository.login.request

import com.example.sobok_android.data.datasource.login.SignInDataSource
import com.example.sobok_android.data.mapper.SignInMapper
import com.example.sobok_android.data.model.request.ReqSignInSuccessData
import com.example.sobok_android.domain.model.login.request.SignInSuccessData
import com.example.sobok_android.domain.repository.login.request.SignInRepository

class SignInRepositoryImpl(private val signInDataSource: SignInDataSource) : SignInRepository {
    override suspend fun postSignInResult(body: ReqSignInSuccessData): SignInSuccessData {
        return SignInMapper.mapperToSignInResultSuccessData(signInDataSource.postSignIn(body))
    }
}



