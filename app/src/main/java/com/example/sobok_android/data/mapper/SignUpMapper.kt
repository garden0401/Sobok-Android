package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.login.ResSignUpSuccessData
import com.example.sobok_android.domain.model.login.request.SignUpSuccessData

object SignUpMapper {

    fun mapperToSignUpResultSuccessData(resSignUpSuccessData: ResSignUpSuccessData): SignUpSuccessData {
        return SignUpSuccessData(
            data = SignUpSuccessData.MemberInfo(resSignUpSuccessData.data.user.id, resSignUpSuccessData.data.user.username, resSignUpSuccessData.data.accesstoken)

        )
    }
}