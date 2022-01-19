package com.example.sobok_android.domain.model.share.request


data class ShareRequestSuccessData(
    val data: MemberInfo,
) {
    data class MemberInfo(
        val memberId: Int,
        val memberName: String,
    )
}