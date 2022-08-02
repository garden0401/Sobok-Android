package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PillAddBottomSheetFragment : BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val pillAddViewModel: PillAddViewModel by sharedViewModel()

    lateinit var _binding: FragmentPillAddBottomSheetBinding
    private val binding get() = _binding!!
    var _isMyPill: Boolean = false
    var _canAddPill: Boolean = false
    var _pillCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observePillCount()

        binding.clPillAddMyPill.setOnClickListener {
            _isMyPill = true
            // getPillCount()
            val intent = Intent(activity, PillAddActivity::class.java)
            startActivity(intent)
        }
        binding.clPillAddSendPill.setOnClickListener {
            _isMyPill = false
            getPillCount()
            val intent = Intent(activity, PillAddActivity::class.java)
            startActivity(intent)
        }
        binding.ivCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun getPillCount() {
        mainViewModel.getPillCount()
    }

    private fun observePillCount() {
        mainViewModel.pillCount.observe(this) {
            _canAddPill = it >= 0
            _pillCount = it
            mainViewModel.setNavigateData(PillAddNavigateData(_canAddPill, _isMyPill, _pillCount))
        }
    }
}
