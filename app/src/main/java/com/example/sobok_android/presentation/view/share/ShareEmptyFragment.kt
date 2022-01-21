package com.example.sobok_android.presentation.view.share

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareEmptyBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.share.request.ShareRequestActivity

class ShareEmptyFragment : BindingFragment<FragmentShareEmptyBinding>(R.layout.fragment_share_empty) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBtnShareRequestClickListener()
    }

    private fun setBtnShareRequestClickListener() {
        binding.btnShareRequest.setOnClickListener {
            startActivity(Intent(requireContext(), ShareRequestActivity::class.java))
        }
    }

}