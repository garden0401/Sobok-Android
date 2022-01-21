package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.databinding.FragmentPillAddBottomSheetBinding
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PillAddBottomSheetFragment : BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    lateinit var _binding: FragmentPillAddBottomSheetBinding
    var pillAddNavigateData = PillAddNavigateData(false, false, 0)
    private val binding get() = _binding!!
    var _isMyPill: Boolean = false
    var _canAddPill: Boolean = false
    var _pillCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPillAddBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observePillCount()
        //observePillAddNavigateData()

        binding.clPillAddMyPill.setOnClickListener {
            _isMyPill = true
            getPillCount()
        }
        binding.clPillAddSendPill.setOnClickListener {
            _isMyPill = false
            getPillCount()
        }
        binding.ivCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun getPillCount() {
        mainViewModel.getPillCount()
    }

    private fun observePillCount() {
        mainViewModel.pillCount.observe(viewLifecycleOwner) {
            //_pillCount = it
            Log.d("약 개수", "$it")
            _canAddPill = it > 0
            Log.d("약추가가능?", "$_canAddPill")
            if(_isMyPill) {
                navigateToMyPillAdd()
            }
            else {
                navigateToSendPillAdd()
            }
        }
    }

    private fun navigateToMyPillAdd() {

        //val intent = Intent(requireContext(), PillAddActivity::class.java)
        // Main Activity 로 값을 넘긴다..............................
        // Main 에서
        pillAddNavigateData.pillCount = _pillCount
        pillAddNavigateData.canAddPill = _canAddPill
        pillAddNavigateData.isMyPill = _isMyPill
        mainViewModel.setPillCount(_pillCount)
        mainViewModel.setIsMyPill(_isMyPill)
        mainViewModel.setCanAddPill(_canAddPill)

        //startActivity(intent)
    }

    private fun navigateToSendPillAdd() {
        pillAddNavigateData.pillCount = _pillCount
        pillAddNavigateData.canAddPill = _canAddPill
        pillAddNavigateData.isMyPill = _isMyPill
        mainViewModel.setPillCount(_pillCount)
        mainViewModel.setIsMyPill(_isMyPill)
        mainViewModel.setCanAddPill(_canAddPill)
        Log.d("BottomSheet에서 MainViewModel isMyPill 값 가져오기", "${mainViewModel.isMyPill}")
        Log.d("BottomSheet에서 MainViewModel canAddPill 값 가져오기", "${mainViewModel.canAddPill}")

        //startActivity(intent)

    }

    fun getMyPIll() : Boolean{
        return _isMyPill
    }
}