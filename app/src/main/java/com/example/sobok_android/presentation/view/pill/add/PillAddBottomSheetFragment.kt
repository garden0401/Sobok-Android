package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PillAddBottomSheetFragment : BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    lateinit var _binding: FragmentPillAddBottomSheetBinding
    private val binding get() = _binding!!
    private var _isMyPill: Boolean = false
    private var _canAddPill: Boolean = false
    private var _pillCount: Int = 0

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
            getPillCount()
            val intent = Intent(activity, PillAddActivity::class.java)
            intent.putExtra("initList", true)
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
            _canAddPill = (it >= 0)
            _pillCount = it
            Log.d("mycount", "${it}")
            mainViewModel.setNavigateData(PillAddNavigateData(_canAddPill, _isMyPill, _pillCount))
        }
    }
}
