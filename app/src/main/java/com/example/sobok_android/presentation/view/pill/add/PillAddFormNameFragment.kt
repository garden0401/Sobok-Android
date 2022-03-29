package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormBinding
import com.example.sobok_android.databinding.FragmentPillAddFormNameBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.pill.add.adapter.PillNameAdapter

class PillAddFormNameFragment : BindingFragment<FragmentPillAddFormNameBinding>(R.layout.fragment_pill_add_form_name) {
    private lateinit var pillNameAdapter: PillNameAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        binding.clTop.setOnTouchListener { v, event ->
            binding.rcvPillName.clearFocus()
            return@setOnTouchListener false
        }
        */

    }

    private fun initPillNameAdapter() {
        pillNameAdapter = PillNameAdapter()
        binding.rcvPillName.adapter = pillNameAdapter

        val list = mutableListOf<String>("null")
        pillNameAdapter.realPillNameList = list
    }


}

