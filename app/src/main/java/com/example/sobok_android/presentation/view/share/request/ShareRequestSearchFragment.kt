package com.example.sobok_android.presentation.view.share.request

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareRequestSearchBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShareRequestSearchFragment :
    BindingFragment<FragmentShareRequestSearchBinding>(R.layout.fragment_share_request_search) {
    private val shareRequestViewModel: ShareRequestViewModel by sharedViewModel()
    private lateinit var shareRequestActivity: Activity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClShareRequestSearchBarClickListener()
        setIsShareRequestSearch()
        setEtSearchSetOnKeyListener()
        setDeleteClickListener()
        observeUserName()
        observeSearchResult()
        setTvShareRequestSearchResult()
        setEtShareRequestSaveClearFocus()
        setEtShareRequestOnFocusChangeListener()
    }

    private fun setIsShareRequestSearch() {
        shareRequestViewModel.setIsShareRequestSearch(true)
    }

    private fun navigateToShareRequestSaveFragment() {
        shareRequestActivity = activity as ShareRequestActivity
        (shareRequestActivity as ShareRequestActivity).replaceSaveFragment()
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
                    binding.nickName = it.data[0].memberName
                    shareRequestViewModel.memberId = it.data[0].memberId
                }
            }
        }
    }

    private fun setTvShareRequestSearchResult() {
        binding.tvShareRequestSearchResult.setOnClickListener {
            navigateToShareRequestSaveFragment()
            shareRequestViewModel.memberName = binding.tvShareRequestSearchResult.text.toString()
        }
    }

    private fun setClShareRequestSearchBarClickListener() {
        binding.clShareRequestSearchBar.setOnClickListener {
            binding.etShareRequestSearch.isSelected = true
        }
    }

    private fun setEtShareRequestSaveClearFocus() {
        binding.clShareRequestSearch.setOnTouchListener { v, event ->
            binding.etShareRequestSearch.clearFocus()
            hideKeyBoard()
            return@setOnTouchListener false
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager: InputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            binding.etShareRequestSearch.windowToken,
            0
        )
    }

    private fun setEtShareRequestOnFocusChangeListener() {
        binding.etShareRequestSearch.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.isEditable = true
            } else {
                binding.isEditable = false
                hideKeyBoard()
            }
        }
    }

}