package com.example.sobok_android.presentation.view.share

import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.home.HomeFragment

class ShareFragment : BindingFragment<FragmentShareBinding>(R.layout.fragment_share) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentContainer()
    }

    private fun setFragmentContainer() {
        childFragmentManager.beginTransaction().add(R.id.fcv_share_calendar, HomeFragment()).commit()
    }
}