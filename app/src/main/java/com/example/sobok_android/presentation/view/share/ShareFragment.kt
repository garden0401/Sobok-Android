package com.example.sobok_android.presentation.view.share

import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentShareBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.home.HomeFragment
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShareFragment : BindingFragment<FragmentShareBinding>(R.layout.fragment_share) {
    private val mainViewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeGroupData()
        setBtnPlusClickListener()

    }

    private fun setCalendarFragment() {
        childFragmentManager.beginTransaction().add(R.id.fcv_share_calendar, HomeFragment())
            .commit()
    }

    private fun observeGroupData() {
        mainViewModel.groupData.observe(viewLifecycleOwner) {
            with(binding) {
                if (it.isEmpty()) {
                    isGroupEmpty = true
                    //TODO: 함수로 정리
                    childFragmentManager.beginTransaction()
                        .add(R.id.fcv_share_calendar, ShareEmptyFragment()).commit()
                } else {
                    isGroupEmpty = false
                    setCalendarFragment()
                    if (it.isNotEmpty()) {
                        //TODO: member이름 한글자인 경우도 가능하도록 수정
                        tvMemberOne.text = it[0].memberName.substring(0, 2)
                        tvMemberOne.isSelected = true
                        mainViewModel.setSelectedMemberName(tvMemberOne.text.toString())
                        tvMemberOne.setTextAppearance(R.style.MemberSelectTextStyle)
                        tvMemberOne.setOnClickListener {
                            if (!tvMemberOne.isSelected) {
                                tvMemberOne.isSelected = true
                                mainViewModel.setSelectedMemberName(tvMemberOne.text.toString())
                                setAllMemberNotSelected()
                                tvMemberOne.setTextAppearance(R.style.MemberSelectTextStyle)
                            }
                        }
                    }
                    if (it.size > 1) {
                        tvMemberTwo.text = it[1].memberName.substring(0, 2)
                        tvMemberTwo.setOnClickListener {
                            if (!tvMemberTwo.isSelected) {
                                tvMemberTwo.isSelected = true
                                mainViewModel.setSelectedMemberName(tvMemberTwo.text.toString())
                                setAllMemberNotSelected()
                                tvMemberTwo.setTextAppearance(R.style.MemberSelectTextStyle)
                            }
                        }
                    }
                    if (it.size > 2) {
                        tvMemberThree.text = it[2].memberName.substring(0, 2)
                        tvMemberThree.setOnClickListener {
                            if (!tvMemberThree.isSelected) {
                                tvMemberThree.isSelected = true
                                mainViewModel.setSelectedMemberName(tvMemberThree.text.toString())
                                setAllMemberNotSelected()
                                tvMemberThree.setTextAppearance(R.style.MemberSelectTextStyle)
                            }
                        }
                    }
                    if (it.size > 3) {
                        tvMemberFour.text = it[3].memberName.substring(0, 2)
                        tvMemberFour.setOnClickListener {
                            if (!tvMemberFour.isSelected) {
                                tvMemberFour.isSelected = true
                                mainViewModel.setSelectedMemberName(tvMemberFour.text.toString())
                                setAllMemberNotSelected()
                                tvMemberFour.setTextAppearance(R.style.MemberSelectTextStyle)
                            }
                        }
                    }
                    if (it.size > 4) {
                        tvMemberFive.text = it[4].memberName.substring(0, 2)
                        tvMemberFive.setOnClickListener {
                            if (!tvMemberFive.isSelected) {
                                tvMemberFive.isSelected = true
                                mainViewModel.setSelectedMemberName(tvMemberFive.text.toString())
                                setAllMemberNotSelected()
                                tvMemberFive.setTextAppearance(R.style.MemberSelectTextStyle)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setAllMemberNotSelected() {
        with(binding) {
            tvMemberOne.isSelected = false
            tvMemberOne.setTextAppearance(R.style.MemberNotSelectTextStyle)
            tvMemberTwo.isSelected = false
            tvMemberTwo.setTextAppearance(R.style.MemberNotSelectTextStyle)
            tvMemberThree.isSelected = false
            tvMemberThree.setTextAppearance(R.style.MemberNotSelectTextStyle)
            tvMemberFour.isSelected = false
            tvMemberFour.setTextAppearance(R.style.MemberNotSelectTextStyle)
            tvMemberFive.isSelected = false
            tvMemberFive.setTextAppearance(R.style.MemberNotSelectTextStyle)
        }
    }

    private fun setBtnPlusClickListener() {
        binding.ivShareRequestPlus.setOnClickListener {
            //TODO: 친구 숫자 세서 5명 넘으면 팝업 띄우기
            //mainViewModel.setIsShareRequestClick(true)
        }
    }

}