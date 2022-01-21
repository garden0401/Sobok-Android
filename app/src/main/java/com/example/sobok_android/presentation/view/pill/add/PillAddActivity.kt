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
        pillAddViewModel.setPillAddNavigetData(
            PillAddNavigateData(
                intent.getBooleanExtra("canAddPill", false),
                intent.getBooleanExtra("isMyPill", false),
                intent.getIntExtra("pillCount", 0)
        ))

        Log.d("Main!!!!!!!!!!!!!!!! 값", "${pillAddViewModel.isMyPill}")
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
            .replace(R.id.fcv_pill_add, pillAddFinishFragment).commit()
    }

    fun replacePillAddFormFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_pill_add, pillAddFormFragment).commit()
    }


}