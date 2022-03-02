package com.example.sobok_android.data.mapper

import com.example.sobok_android.data.model.response.login.ResSignInSuccessData
import com.example.sobok_android.domain.model.login.request.SignInSuccessData

object SignInMapper {

    fun mapperToSignInResultSuccessData(resSignInSuccessData: ResSignInSuccessData): SignInSuccessData {
        return SignInSuccessData(
            data = SignInSuccessData.MemberInfo(resSignInSuccessData.data.id, resSignInSuccessData.data.username, resSignInSuccessData.data.accessToken)

        )
    }
}






