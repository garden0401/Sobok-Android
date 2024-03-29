package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormNameBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillNameAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.util.dp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PillAddFormNameFragment :
    BindingFragment<FragmentPillAddFormNameBinding>(R.layout.fragment_pill_add_form_name) {
    private lateinit var pillNameAdapter: PillNameAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private val pillAddViewModel: PillAddViewModel by sharedViewModel()
    private var pillCount: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPillNameAdapter()
        addPillPerson()
        navigateToHome()
        initBottomSheet()
        setBottomSheetStateChanged()


        binding.isMyPill = pillAddViewModel.pillAddNavigateData.value?.isMyPill
        val isMyPill = pillAddViewModel.pillAddNavigateData.value?.isMyPill
        Log.d("ISMYPILL", "${pillAddViewModel.pillAddNavigateData.value?.isMyPill}")

        pillCount = 5 - pillAddViewModel.pillAddNavigateData.value?.pillCount!!
        binding.tvPillAddNameNum.text = pillCount.toString()

        //binding.includeBottomsheet.tvCycleDetail.text = pillAddViewModel.pillCycle.value.toString()


        binding.clBtnNext.setOnClickListener {
            if(isMyPill == true) {
                // 걍 내약 추가하셈
                Log.d("하이요", "hihi")

                Log.d("cycle", "${pillAddViewModel.getCycle()}")
                Log.d("period", "${pillAddViewModel.getPillPeriod()}")
                Log.d("days", "${pillAddViewModel.getPillDays()}")
                Log.d("start", "${pillAddViewModel.start.value}")
                Log.d("end", "${pillAddViewModel.end.value}")
                Log.d("nameLisst", "${pillAddViewModel.pillNameList}")
            }
//            BottomSheetBehavior.from(binding.bottomSheet).state =
//                BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }

    private fun initBottomSheet() {
//        val bottomSheet = binding.bottomSheet
//        val behavior = BottomSheetBehavior.from(bottomSheet)
//        with(behavior) {
//
//            isHideable = true
//            state = BottomSheetBehavior.STATE_HIDDEN
//            isFitToContents = false
//            // 40dp를 픽셀로 ㄱㅖ산
//            expandedOffset = 40.dp
//            saveFlags = BottomSheetBehavior.SAVE_ALL
//            // 숨길때 끝까지 숨길지
//            peekHeight = 0
//        }
    }

    private fun setBottomSheetStateChanged() {
//        BottomSheetBehavior.from(binding.bottomSheet)
//            .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                    // slide
//                    binding.bg.visibility = View.VISIBLE
//                    binding.bg.isClickable = true
//
//                }
//
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                        binding.bg.visibility = View.GONE
//                    }
//                    if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
//                        // bottomsheet의 cl_expand 가리기
//                        binding.includeBottomsheet.clExtend.visibility = View.GONE
//                    }
//                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//                        // bottomsheet의 cl_expand 보이기
//                        binding.includeBottomsheet.clExtend.visibility = View.VISIBLE
//                    }
//                }
//            })
    }


    private fun addPillPerson() {

        binding.clAddPillName.setOnClickListener {
//            if (pillNameAdapter.itemCount < 5) {
//                val list = pillNameAdapter.realPillNameList
//                list.add("null")
//                pillNameAdapter.realPillNameList = list
//            }
            if(pillNameAdapter.itemCount < pillCount) {
                pillAddViewModel.addName("")
                pillNameAdapter.submitList(pillAddViewModel.pillNameList)
                pillNameAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initPillNameAdapter() {
        pillNameAdapter = PillNameAdapter(::deleteName)
        pillNameAdapter.submitList(pillAddViewModel.pillNameList)
        binding.rcvPillName.adapter = pillNameAdapter
    }

    private fun deleteName(name: String) {
        pillAddViewModel.deleteName(name)
        pillNameAdapter.submitList(pillAddViewModel.pillNameList)
    }

    private fun navigateToHome() {
        binding.clBtnNext.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

