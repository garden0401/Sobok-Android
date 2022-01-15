package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddFinishBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillListAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PillAddFinishActivity :
    BindingActivity<ActivityPillAddFinishBinding>(R.layout.activity_pill_add_finish) {
    private val pillAddViewModel: PillAddViewModel by viewModel()
    private lateinit var pillListAdapter: PillListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPillListAdapter()
        getPillList()
        setPillList()
        observePillList()
        navigateToHome()
    }

    private fun initPillListAdapter() {
        pillListAdapter = PillListAdapter()
        binding.rcvPillList.adapter = pillListAdapter
    }

    private fun getPillList() {
        pillAddViewModel.getPillList()
    }

    private fun setPillList() {
        pillListAdapter.setDeletePillItemListener {
            pillAddViewModel.setPillList(it)
        }
    }

    private fun observePillList() {
        pillAddViewModel.pillList.observe(this) {
            pillListAdapter.pillList = it.pillList
        }
    }

    private fun navigateToHome() {
        binding.clAddNewPill.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
