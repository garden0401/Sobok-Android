package com.example.sobok_android.presentation.view.share.request.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.share.request.SearchResultData
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

    fun getSearchResult() = viewModelScope.launch {
        runCatching { shareRequestRepository.getSearchResult(requireNotNull(_userName.value)) }
            .onSuccess {
                _searchResult.postValue(it)
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}