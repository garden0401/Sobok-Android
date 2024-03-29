package com.example.sobok_android.presentation.view.notice.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.notice.NoticeListData
import com.example.sobok_android.domain.repository.notice.NoticeRepository
import kotlinx.coroutines.launch

class NoticeViewModel(private val noticeRepository: NoticeRepository): ViewModel() {

    private val _noticeListData = MutableLiveData<NoticeListData>()
    val noticeListData: LiveData<NoticeListData>
        get() = _noticeListData

    fun getNoticeList() = viewModelScope.launch {
        runCatching { noticeRepository.getNoticeList() }
            .onSuccess {
                _noticeListData.postValue(it)
                Log.d("getNoticeList", it.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.d("getNoticeList-fail", "fail")
            }
    }


}