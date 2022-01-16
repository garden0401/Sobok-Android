package com.example.sobok_android.presentation.view.pill.add.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import kotlinx.coroutines.launch

class PillAddViewModel(val pillAddRepository: PillAddRepository) : ViewModel() {

    private val _pillList = MutableLiveData<PillListData>()
    val pillList: LiveData<PillListData> get() = _pillList

    fun setPillList(position: Int) {
        _pillList.value!!.pillList.removeAt(position)
        _pillList.value = PillListData(_pillList.value!!.pillList)
    }

    fun getPillList() = viewModelScope.launch {
        runCatching { pillAddRepository.getPillList() }
            .onSuccess {
                _pillList.postValue(it) // _pillList = it
            }
            .onFailure {
                Log.d("server-pill-add-read-fail", "실패할일이 없다")
            }
    }





}