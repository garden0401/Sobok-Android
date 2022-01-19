package com.example.sobok_android.presentation.view.share.request.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.share.request.SearchResultData
import com.example.sobok_android.domain.model.share.request.ShareRequestSuccessData
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import kotlinx.coroutines.launch

class ShareRequestViewModel(private val shareRequestRepository: ShareRequestRepository) :
    ViewModel() {

    private val _searchResult = MutableLiveData<SearchResultData>()
    var searchResult: LiveData<SearchResultData> = _searchResult

    private val _userName = MutableLiveData<String>()
    var userName: LiveData<String> = _userName
    fun setUserName(value: String) {
        _userName.value = value
    }

    private var _memberName: String = ""
    var memberName: String = _memberName
        set(value) {
            _memberName = value
            field = value
        }

    private var _memberId: Int = 0
    var memberId: Int = _memberId
        set(value) {
            _memberId = value
            field = value
        }

    private lateinit var _memberInfo: ShareRequestSuccessData.MemberInfo

    private val _isShareRequestSearch = MutableLiveData<Boolean>()
    var isShareRequestSearch: LiveData<Boolean> = _isShareRequestSearch
    fun setIsShareRequestSearch(value: Boolean) {
        _isShareRequestSearch.value = value
    }

    fun getSearchResult() = viewModelScope.launch {
        runCatching { shareRequestRepository.getSearchResult(requireNotNull(_userName.value)) }
            .onSuccess {
                _searchResult.postValue(it)
                Log.d("searchResult-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
                Log.d("searchResult-server", "fail${it.message}")
            }
    }

    fun postSearchResult() = viewModelScope.launch {
        runCatching { shareRequestRepository.postSearchResult(_memberId, _memberName) }
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