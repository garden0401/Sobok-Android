package com.example.sobok_android.presentation.view.share.request

import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityShareRequestBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShareRequestActivity :
    BindingActivity<ActivityShareRequestBinding>(R.layout.activity_share_request) {
    private val shareRequestViewModel: ShareRequestViewModel by viewModel()
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var shareRequestSearchFragment: ShareRequestSearchFragment
    private lateinit var shareRequestSaveFragment: ShareRequestSaveFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTransactionEvent()
        observeIsShareRequestSearch()
        observeIsShareRequest()
    }

    private fun initTransactionEvent() {
        shareRequestSearchFragment = ShareRequestSearchFragment()
        shareRequestSaveFragment = ShareRequestSaveFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_share_request, shareRequestSearchFragment).commit()

        binding.ivShareRequestBack.setOnClickListener {
            replaceSearchFragment()
        }

        binding.ivShareRequestClose.setOnClickListener {
            finish()
        }
    }

    private fun observeIsShareRequestSearch() {
        shareRequestViewModel.isShareRequestSearch.observe(this) {
            binding.isShareRequestSearch = it
        }
    }

    fun replaceSaveFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_share_request, shareRequestSaveFragment).commit()
    }

    fun replaceSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_share_request, shareRequestSearchFragment).commit()
    }

    private fun observeIsShareRequest() {
        mainViewModel.isShareRequest.observe(this) {
            if(it) {
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}