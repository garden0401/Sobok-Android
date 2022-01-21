package com.example.sobok_android.domain.model.share.request


data class GroupData(
    val data: List<MemberInfo>
) {
    data class MemberInfo(
        val memberId: Int,
        val memberName: String
    )
}