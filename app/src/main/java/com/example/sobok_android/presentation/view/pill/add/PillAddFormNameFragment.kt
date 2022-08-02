package com.example.sobok_android.presentation.view.pill.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormNameBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillNameAdapter
import com.example.sobok_android.util.dp
import com.google.android.material.bottomsheet.BottomSheetBehavior


class PillAddFormNameFragment :
    BindingFragment<FragmentPillAddFormNameBinding>(R.layout.fragment_pill_add_form_name) {
    private lateinit var pillNameAdapter: PillNameAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPillNameAdapter()
        addPillPerson()
        navigateToHome()
        initBottomSheet()
        setBottomSheetStateChanged()

        binding.clBtnNext.setOnClickListener {
            BottomSheetBehavior.from(binding.bottomSheet).state =
                BottomSheetBehavior.STATE_HALF_EXPANDED
        }

    }


    private fun initBottomSheet() {
        val bottomSheet = binding.bottomSheet
        val behavior = BottomSheetBehavior.from(bottomSheet)
        with(behavior) {

            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN
            isFitToContents = false
            // 40dp를 픽셀로 ㄱㅖ산
            expandedOffset = 40.dp
            saveFlags = BottomSheetBehavior.SAVE_ALL
            // 숨길때 끝까지 숨길지
            peekHeight = 0
        }
    }

    private fun setBottomSheetStateChanged() {
        BottomSheetBehavior.from(binding.bottomSheet)
            .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    // slide
                    binding.bg.visibility = View.VISIBLE
                    binding.bg.isClickable = true

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        binding.bg.visibility = View.GONE
                    }
                    if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        // bottomsheet의 cl_expand 가리기
                        binding.includeBottomsheet.clExtend.visibility = View.GONE
                    }
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        // bottomsheet의 cl_expand 보이기
                        binding.includeBottomsheet.clExtend.visibility = View.VISIBLE
                    }
                }
            })
    }


    private fun addPillPerson() {

        binding.clAddPillName.setOnClickListener {
            if (pillNameAdapter.itemCount < 5) {
                val list = pillNameAdapter.realPillNameList
                list.add("null")
                pillNameAdapter.realPillNameList = list
            }
        }
    }

    private fun initPillNameAdapter() {
        pillNameAdapter = PillNameAdapter()
        binding.rcvPillName.adapter = pillNameAdapter

        val list = mutableListOf<String>("null")
        pillNameAdapter.realPillNameList = list
    }

    private fun navigateToHome() {
        binding.clBtnNext.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }


}

