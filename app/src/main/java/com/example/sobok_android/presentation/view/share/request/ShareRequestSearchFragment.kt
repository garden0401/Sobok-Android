package com.example.sobok_android.presentation.view.share.request

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareRequestSearchBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import com.example.sobok_android.util.closeKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShareRequestSearchFragment :
    BindingFragment<FragmentShareRequestSearchBinding>(R.layout.fragment_share_request_search) {
    private val shareRequestViewModel: ShareRequestViewModel by sharedViewModel()
    private lateinit var bundle: Bundle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickEvent()
        setEtSearchSetOnKeyListener()
        observeSearchResult()
        setEtShareRequestSaveClearFocus()
        setEtShareRequestOnFocusChangeListener()
    }

    private fun setEtSearchSetOnKeyListener() {
        with(binding) {
            //TODo: enter키 체크
            etShareRequestSearch.setOnKeyListener { v, keyCode, event ->
                if (event.action == ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    requireContext().closeKeyboard(etShareRequestSearch)
                    if (etShareRequestSearch.text.isNotBlank()) {
                        shareRequestViewModel.getSearchResult(etShareRequestSearch.text.toString())
                    } else {
                        isEmpty = true
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }

    private fun initClickEvent() {
        with(binding) {
            clShareRequestSearchBar.setOnClickListener {
                etShareRequestSearch.isSelected = true
            }
            ivShareRequestSearchDelete.setOnClickListener {
                etShareRequestSearch.text = null
                etShareRequestSearch.requestFocus()
            }
            tvShareRequestSearchResult.setOnClickListener {
                findNavController().navigate(
                    R.id.action_shareRequestSearchFragment_to_shareRequestSaveFragment,
                    bundle
                )
            }
        }
    }

    private fun observeSearchResult() {
        shareRequestViewModel.searchResult.observe(viewLifecycleOwner) {
            when (it.data.isEmpty()) {
                true -> binding.isEmpty = true
                else -> {
                    binding.isEmpty = false
                    binding.nickName = it.data[0].memberName
                    bundle = Bundle().apply {
                        putString("memberName", it.data[0].memberName)
                        putInt("memberId", it.data[0].memberId)
                    }
                }
            }
        }
    }

    private fun setEtShareRequestSaveClearFocus() {
        binding.clShareRequestSearch.setOnClickListener {
            binding.etShareRequestSearch.clearFocus()
            requireContext().closeKeyboard(binding.etShareRequestSearch)
        }
    }


    private fun setEtShareRequestOnFocusChangeListener() {
        binding.etShareRequestSearch.setOnFocusChangeListener { view, hasFocus ->
            binding.isEditable = hasFocus
        }
    }

}