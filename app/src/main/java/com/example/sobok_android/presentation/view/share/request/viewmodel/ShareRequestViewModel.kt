package com.example.sobok_android.presentation.view.share.request.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.request.share.ReqShareRequestData
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import kotlinx.coroutines.launch

class ShareRequestViewModel(private val shareRequestRepository: ShareRequestRepository) :
    ViewModel() {

    private val _searchResult = MutableLiveData<SearchResultData>()
    var searchResult: LiveData<SearchResultData> = _searchResult

    private lateinit var _memberInfo: ShareRequestSuccessData.MemberInfo

    private val _isShareRequestClick = MutableLiveData<Boolean>(false)
    var isShareRequestClick: LiveData<Boolean> = _isShareRequestClick

    fun setIsShareRequestClick(value: Boolean) {
        _isShareRequestClick.value = value
    }

    fun getSearchResult(searchWord: String) = viewModelScope.launch {
        runCatching { shareRequestRepository.getSearchResult(searchWord) }
            .onSuccess {
                _searchResult.postValue(it)
                Log.d("searchResult-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("searchResult-server", "fail${it.message}")
            }
    }

    fun postSearchResult(memberId: Int, memberName: String) = viewModelScope.launch {
        runCatching {
            shareRequestRepository.postSearchResult(memberId, ReqShareRequestData(memberName))
        }
            .onSuccess {
                _memberInfo = it.data
                //TODO sharePreference에 멤버 값 저장
                Log.d("searchResultSuccess-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}