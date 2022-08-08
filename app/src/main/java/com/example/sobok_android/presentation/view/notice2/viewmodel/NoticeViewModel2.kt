package com.example.sobok_android.presentation.view.notice2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.request.notice.ReqNoticeCalendarShareData
import com.example.sobok_android.domain.model.notice2.NoticeDetailData
import com.example.sobok_android.domain.model.notice2.NoticeGenericData
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.domain.repository.notice2.NoticeRepository2
import kotlinx.coroutines.launch

class NoticeViewModel2(private val noticeRepository2: NoticeRepository2): ViewModel() {

    // 알림 정보 전체 조회
    private val _noticeListData2 = MutableLiveData<NoticeListData2>()
    val noticeListData2: LiveData<NoticeListData2>
        get() = _noticeListData2

    fun getNoticeList() = viewModelScope.launch {
        kotlin.runCatching { noticeRepository2.getNoticeList() }
            .onSuccess {
                _noticeListData2.postValue(it)
                Log.d("getNoticeList", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("getNoticeList-fail", it.toString())
            }
    }

    // 캘린더 공유 수락&거절

//    private var _sendGroupId = MutableLiveData<Int>()
//    val sendGroupId : LiveData<Int> = _sendGroupId
//    fun postSendGroupId(value: Int) {
//        _sendGroupId.value = value
//    }


    fun putNoticeCalendarShare(sendGroupId: Int, isOkay: String) = viewModelScope.launch {
        kotlin.runCatching { noticeRepository2.putNoticeCalendarShare(sendGroupId, ReqNoticeCalendarShareData(isOkay))}
            .onSuccess {
                Log.d("putNoticeCalendarShare-success", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("putNoticeCalendarShare-fail", it.toString())
            }
    }

    // 알림_ 타인에게 받은 약 수락&거절
    fun putNoticePillAccept(pillId: Int, isOkay: String) = viewModelScope.launch {
        kotlin.runCatching { noticeRepository2.putNoticePillAccept(pillId, ReqNoticeCalendarShareData(isOkay)) }
            .onSuccess {
                Log.d("putNoticePillAccept-success", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("putNoticePillAccept-fail", it.toString())
            }
    }

    // 알림 상세보기 약 정보 조회
    private val _noticeDetailData = MutableLiveData<NoticeDetailData>()
    val noticeDetailData: LiveData<NoticeDetailData>
        get() = _noticeDetailData

    fun getNoticeDetail(noticeId: Int, pillId: Int) = viewModelScope.launch {
        kotlin.runCatching { noticeRepository2.getNoticeDetail(noticeId, pillId) }
            .onSuccess {
                _noticeDetailData.postValue(it)
                Log.d("getNoticeDetail", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("getNoticeDetail-fail", it.toString())
            }
    }


}