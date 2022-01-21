package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFinishBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillListAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PillAddFinishFragment :
    BindingFragment<FragmentPillAddFinishBinding>(R.layout.fragment_pill_add_finish) {
    private val pillAddViewModel: PillAddViewModel by sharedViewModel()
    private lateinit var pillListAdapter: PillListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPillListAdapter()
        //getPillList()
        //observePillList()
        navigateToPillAdd()
        navigateToHome()
    }

    private fun initPillListAdapter() {
        pillListAdapter = PillListAdapter()
        pillListAdapter.pillList = pillAddViewModel.pillList
        binding.rcvPillList.adapter = pillListAdapter
    }

    private fun navigateToPillAdd() {
        binding.clAddNewPill.setOnClickListener {
            val intent = Intent(requireContext(), PillAddFormFragment::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToHome() {
        binding.tvFinish.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}
