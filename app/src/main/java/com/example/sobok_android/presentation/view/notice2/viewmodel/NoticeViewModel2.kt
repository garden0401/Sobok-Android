package com.example.sobok_android.presentation.view.notice2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.notice2.NoticeListData2
import com.example.sobok_android.domain.repository.notice2.NoticeRepository2
import kotlinx.coroutines.launch

class NoticeViewModel2(private val noticeRepository2: NoticeRepository2): ViewModel() {

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
}