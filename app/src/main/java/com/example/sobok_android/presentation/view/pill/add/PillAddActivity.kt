package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PillAddActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add) {
    private val pillAddViewModel: PillAddViewModel by viewModel()
    private lateinit var pillAddFormFragment: PillAddFormFragment
    private var pillAddFormNameFragment: PillAddFormNameFragment = PillAddFormNameFragment()
    private var pillAddFormDateFragment: PillAddFormDateFragment = PillAddFormDateFragment()
    private var pillAddFinishBottomSheetFragment: PillAddFinishBottomSheetFragment =
        PillAddFinishBottomSheetFragment()

    private var pillAddNavigateData = PillAddNavigateData(false, false, 0)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pillAddViewModel.setPillAddNavigetData(
            PillAddNavigateData(
                intent.getBooleanExtra("canAddPill", false),
                intent.getBooleanExtra("isMyPill", false),
                intent.getIntExtra("pillCount", 0)
            )
        )
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        pillAddFormFragment = PillAddFormFragment()
        pillAddFormDateFragment = PillAddFormDateFragment()
        pillAddFormNameFragment = PillAddFormNameFragment()
        pillAddFinishBottomSheetFragment = PillAddFinishBottomSheetFragment()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_pill_add).navigateUp()
}