package com.example.sobok_android.presentation.view.share.request

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareRequestSaveBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.regex.Pattern


class ShareRequestSaveFragment :
    BindingFragment<FragmentShareRequestSaveBinding>(R.layout.fragment_share_request_save) {
    private val shareRequestViewModel: ShareRequestViewModel by sharedViewModel()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: 2022-01-19 editText 터치 영역 수정
        setIsShareRequest()
        setIsShareRequestSearch()
        initNickName()
        setEtShareRequestSaveTextChangedListener()
        setEtSearchSetOnKeyListener()
        setEtShareRequestSaveSetFilters()
        setDeleteClickListener()
        setEtShareRequestSaveClearFocus()
        setEtShareRequestOnFocusChangeListener()
    }

    private fun setIsShareRequest() {
        mainViewModel.setIsShareRequest(false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (requireActivity() as ShareRequestActivity).replaceSearchFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressedCallback.remove()
    }

    private fun setIsShareRequestSearch() {
        shareRequestViewModel.setIsShareRequestSearch(false)
    }

    private fun initNickName() {
        val nickname: String = shareRequestViewModel.memberName
        binding.nickName = nickname
        binding.textSize = 0
        binding.isEditable = false
        binding.isError = false
    }

    private fun setEtSearchSetOnKeyListener() {
        binding.etShareRequestSave.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                hideKeyBoard()
                shareRequestViewModel.setUserName(binding.etShareRequestSave.text.toString())
                postShareRequest()
                //TODO:2022-01-19 이미 공유된 아이디인 경우 뷰가 없음 [질문]
                mainViewModel.setIsShareRequest(true)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun postShareRequest() {
        shareRequestViewModel.postSearchResult()
    }

    private fun setEtShareRequestSaveTextChangedListener() {
        binding.etShareRequestSave.addTextChangedListener {
            if (it != null) {
                binding.textSize = it.length
                if (it.length < 2) {
                    binding.isError = true
                    binding.clShareRequestSaveBar.setBackgroundResource(R.drawable.rectangle_border_pill_color_red_radius_12)
                    binding.tvShareRequestSaveError.text =
                        getString(R.string.share_request_save_error_more)
                } else {
                    binding.isError = false
                    binding.clShareRequestSaveBar.setBackgroundResource(R.drawable.selector_share_request_search_bar)
                }
            } else {
                binding.isError = false
                binding.isEditable = false
            }
        }
    }

    private fun setEtShareRequestSaveSetFilters() {
        binding.etShareRequestSave.filters =
            arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
                val notSpecialCharPattern: Pattern =
                    Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")
                if (source == "" || notSpecialCharPattern.matcher(source).matches()) {
                    binding.clShareRequestSaveBar.setBackgroundResource(R.drawable.selector_share_request_search_bar)
                    binding.isError = false
                    return@InputFilter source
                }
                binding.isError = true
                binding.tvShareRequestSaveError.text =
                    getString(R.string.share_request_save_error_special)
                binding.clShareRequestSaveBar.setBackgroundResource(R.drawable.rectangle_border_pill_color_red_radius_12)
                ""
            }, LengthFilter(10))
    }

    private fun setDeleteClickListener() {
        binding.ivShareRequestSaveDelete.setOnClickListener {
            binding.etShareRequestSave.text = null
        }
    }

    private fun setEtShareRequestSaveClearFocus() {
        binding.clShareRequestSave.setOnTouchListener { v, event ->
            binding.etShareRequestSave.clearFocus()
            return@setOnTouchListener false
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager: InputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            binding.etShareRequestSave.windowToken,
            0
        )
    }

    private fun setEtShareRequestOnFocusChangeListener() {
        binding.etShareRequestSave.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.isEditable = true
            } else {
                binding.isError = false
                binding.isEditable = false
                binding.clShareRequestSaveBar.isSelected = false
                binding.clShareRequestSaveBar.setBackgroundResource(R.drawable.selector_share_request_search_bar)
                hideKeyBoard()
            }
        }
    }
}
