package com.example.sobok_android.presentation.view.share.request

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareRequestSearchBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShareRequestSearchFragment :
    BindingFragment<FragmentShareRequestSearchBinding>(R.layout.fragment_share_request_search) {
    private val shareRequestViewModel: ShareRequestViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEtSearchSetOnKeyListener()
        setDeleteClickListener()
        observeUserName()
        observeSearchResult()
        setTvShareRequestSearchResult()
    }

    private fun navigateToShareRequestSaveFragment() {
        findNavController().navigate(R.id.action_shareRequestSearchFragment_to_shareRequestSaveFragment)
    }

    private fun setEtSearchSetOnKeyListener() {
        binding.etShareRequestSearch.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == ACTION_DOWN) {
                val inputMethodManager: InputMethodManager =
                    requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    binding.etShareRequestSearch.windowToken,
                    0
                )
                shareRequestViewModel.setUserName(binding.etShareRequestSearch.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun getSearchResult() {
        shareRequestViewModel.getSearchResult()
    }

    private fun observeUserName() {
        shareRequestViewModel.userName.observe(viewLifecycleOwner) {
            getSearchResult()
        }
    }

    private fun setDeleteClickListener() {
        binding.ivShareRequestSearchDelete.setOnClickListener {
            binding.etShareRequestSearch.text = null
        }
    }


    private fun observeSearchResult() {
        shareRequestViewModel.searchResult.observe(viewLifecycleOwner) {
            when (it.data.isEmpty()) {
                true -> binding.isEmpty = true
                else -> {
                    binding.isEmpty = false
                    //TODO:2021-01-18 추후 수정 예정 -> 멤버 리스트 개수 조정
                    binding.nickName = it.data[0].memberName
                }
            }
        }
    }

    private fun setTvShareRequestSearchResult() {
        binding.tvShareRequestSearchResult.setOnClickListener {
            navigateToShareRequestSaveFragment()
        }
    }

}