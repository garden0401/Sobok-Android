package com.example.sobok_android.domain.model.share.request

data class SearchResultData(
    val data: List<Member>,
) {
    data class Member(
        val memberId: Int,
        val memberName: String
    )
}