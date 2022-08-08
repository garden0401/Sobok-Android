package com.example.sobok_android.presentation.view.myinfo

import android.content.Intent
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMyInfoBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.myinfo.adapter.MyPillManageAdapter
import com.example.sobok_android.presentation.view.myinfo.viewmodel.MyInfoViewModel
import com.tomergoldst.tooltips.ToolTip
import com.tomergoldst.tooltips.ToolTipsManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyInfoActivity : BindingActivity<ActivityMyInfoBinding>(R.layout.activity_my_info) {
    private val myInfoViewModel: MyInfoViewModel by viewModel()
    private lateinit var myPillManageAdapter: MyPillManageAdapter
    private lateinit var toolTipsManager: ToolTipsManager
    private lateinit var toolTipBuilder: ToolTip.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initAdapter()
        initClickEvent()
        observeMyPillList()
    }

    private fun initData() {
        //TODO: 원래닉네임 보내기
        binding.nickName = "닉네임"
        toolTipsManager = ToolTipsManager()
        myInfoViewModel.getMyPillList()
    }

    private fun initAdapter() {
        myPillManageAdapter = MyPillManageAdapter()
        binding.rvMyPill.adapter = myPillManageAdapter
    }

    private fun observeMyPillList() {
        myInfoViewModel.myPillList.observe(this) {
            myPillManageAdapter.myPillList = it
        }
    }

    private fun initClickEvent() {
        with(binding) {
            ibNameEdit.setOnClickListener {
                startActivity(Intent(this@MyInfoActivity, MyNicknameEditActivity::class.java))
            }
            ibPillNote.setOnClickListener {
                showTooltip(ToolTip.POSITION_BELOW, ToolTip.ALIGN_LEFT)
                //TODO: tooltip dismiss
                //toolTipsManager.dismissAll()
            }
            ibBack.setOnClickListener {
                finish()
            }
        }
    }

    //TODO: Tooltip 공통함수
    private fun showTooltip(position: Int, align: Int) {
        toolTipsManager.findAndDismiss(binding.ibPillNote)

        val message = getString(R.string.my_info_note)
        toolTipBuilder = ToolTip.Builder(
            this,
            binding.ibPillNote, binding.clMyInfo, message, position
        )

        toolTipBuilder.setAlign(align)
            .setBackgroundColor(resources.getColor(R.color.gray700_sub, theme))
        toolTipsManager.show(toolTipBuilder.build())

    }

}