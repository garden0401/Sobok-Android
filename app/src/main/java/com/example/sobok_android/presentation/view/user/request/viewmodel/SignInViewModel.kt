package com.example.sobok_android.presentation.view.user.request.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobok_android.data.model.request.login.ReqSignInSuccessData
import com.example.sobok_android.data.sharedpref.SobokSharedPreference
import com.example.sobok_android.domain.model.login.request.SignInSuccessData
import com.example.sobok_android.domain.repository.login.request.SignInRepository
import kotlinx.coroutines.launch


class SignInViewModel(private val signInRepository: SignInRepository) :
    ViewModel() {

    private var _memberInfo = MutableLiveData<SignInSuccessData.MemberInfo>()
    val memberInfo : LiveData<SignInSuccessData.MemberInfo> = _memberInfo

    private var _email: String = ""
    var email: String = _email
        set(value) {
            _email = value
            field = value
        }

    private var _password: String = ""
    var password: String = _password
        set(value) {
            _password = value
            field = value
        }

    fun postSignIn() = viewModelScope.launch() {
        runCatching { signInRepository.postSignInResult(ReqSignInSuccessData(_email, _password)) }
            .onSuccess {
                _memberInfo.postValue(it.data)
                SobokSharedPreference.setUserToken(it.data.accesstoken)
                Log.d("searchResultSuccess-server", "success${it}")
            }
            .onFailure {
                it.printStackTrace()
            }
    }
}