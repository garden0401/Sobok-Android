package com.example.sobok_android.presentation.view.myinfo

import android.content.Intent
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityMyInfoBinding
import com.example.sobok_android.domain.model.myinfo.MyPillData
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.myinfo.adapter.MyPillManageAdapter
import com.tomergoldst.tooltips.ToolTip
import com.tomergoldst.tooltips.ToolTipsManager


class MyInfoActivity : BindingActivity<ActivityMyInfoBinding>(R.layout.activity_my_info) {
    private lateinit var myPillManageAdapter: MyPillManageAdapter
    private lateinit var toolTipsManager: ToolTipsManager
    private lateinit var toolTipBuilder: ToolTip.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initAdapter()
        initClickEvent()
    }

    private fun initData() {
        //TODO: 원래닉네임 보내기
        binding.nickName = "닉네임"

        toolTipsManager = ToolTipsManager()
    }

    private fun initAdapter() {
        myPillManageAdapter = MyPillManageAdapter()
        binding.rvMyPill.adapter = myPillManageAdapter
        myPillManageAdapter.myPillList = listOf(
            MyPillData(1, "비타민", "2"),
            MyPillData(1, "비타2", "3"),
            MyPillData(1, "비타3", "1"),
            MyPillData(1, "비타4", "4"),
            MyPillData(1, "비타민", "2")
        )
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