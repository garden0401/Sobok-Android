package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import android.util.Log
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PillAddActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add) {
    private val pillAddViewModel: PillAddViewModel by viewModel()
    private lateinit var pillAddFormFragment: PillAddFormFragment
    private var pillAddFinishFragment: PillAddFinishFragment = PillAddFinishFragment()
    private var pillAddNavigateData = PillAddNavigateData(false, false, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTransactionEvent()

        // intent get으로 Data Class 값 가져와야함.

        pillAddViewModel.isMyPill = intent.getBooleanExtra("isMyPill", false)
        pillAddViewModel.canAddPill = intent.getBooleanExtra("canAddPill", false)
        pillAddViewModel.pillCount = intent.getIntExtra("pillCount", 0)
        Log.d("Main!!!!!!!!!!!!!!!! 값", "${pillAddViewModel.isMyPill}")
        pillAddNavigateData.isMyPill = pillAddViewModel.isMyPill
        pillAddNavigateData.canAddPill = pillAddViewModel.canAddPill
        Log.d("PillAddActivity에 값이 들어오길 바란다..", "$pillAddNavigateData")
    }

    private fun initTransactionEvent() {
        pillAddFormFragment = PillAddFormFragment()
        pillAddFinishFragment = PillAddFinishFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_pill_add, pillAddFormFragment)
            .commit()
    }

    fun replacePillAddFinishFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_pill_finish, pillAddFinishFragment).commit()
    }


}