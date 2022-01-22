package com.example.sobok_android.presentation.view.pill.add

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFinishBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.pill.add.adapter.PillListAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.absoluteValue

class PillAddFinishFragment :
    BindingFragment<FragmentPillAddFinishBinding>(R.layout.fragment_pill_add_finish) {
    private val pillAddViewModel: PillAddViewModel by sharedViewModel()
    private lateinit var pillListAdapter: PillListAdapter
    private var pillListCount: Int = 0
    private var pillCount: Int = 0
    private var possibleCount: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPillListAdapter()
        navigateToPillAdd()
        navigateToHome()

        binding.ivCancel.setOnClickListener {
            val pillAddActivity = (activity as PillAddActivity)
            pillAddActivity.replacePillAddFormFragment()
        }

        observePossibleCount()
        pillCount = pillAddViewModel.pillCount
        pillListCount = pillListAdapter.itemCount
        possibleCount = pillCount-pillListCount
        setPillCountMsg()
        // list item 을 가져와서
        // pillCount - listCount > 0


    }

    private fun observePossibleCount() {
        pillListAdapter.setPillListCountListerner {
            possibleCount = pillAddViewModel.pillCount - it
            setPillCountMsg()
        }

    }
    private fun setPillCountMsg() {
        if (possibleCount > 0) {
            binding.tvAddPillCountMsg.visibility = View.VISIBLE
            binding.tvAddPillCountOverMsg.visibility = View.GONE
            binding.tvAddPillCountMsg.text = "${possibleCount}개 더 추가할 수 있어요"
        } else if (possibleCount == 0) {
            binding.clAddNewPill.visibility = View.GONE
            binding.tvAddPillCountMsg.visibility = View.VISIBLE
            binding.tvAddPillCountOverMsg.visibility = View.GONE
            binding.tvAddPillCountMsg.text = "${possibleCount}개 더 추가할 수 있어요"
        } else {
            binding.clAddNewPill.visibility = View.GONE
            binding.tvAddPillCountMsg.visibility = View.GONE
            binding.tvAddPillCountOverMsg.visibility = View.VISIBLE
            binding.tvAddPillCountOverMsg.text =
                "저장 가능한 약 개수가 ${possibleCount.absoluteValue}개 초과되었어요"
            binding.tvFinish.setBackgroundColor(Color.parseColor("#e3e8eb"))
            binding.tvFinish.setTextColor(Color.parseColor("#a1a9ae"))
            binding.tvFinish.isClickable = false
        }
    }


    private fun initPillListAdapter() {
        pillListAdapter = PillListAdapter()
        pillListAdapter.pillList = pillAddViewModel.pillList
        binding.rcvPillList.adapter = pillListAdapter
        //
    }

    private fun navigateToPillAdd() {
        binding.clAddNewPill.setOnClickListener {
//            val intent = Intent(requireContext(), PillAddFormFragment::class.java)
//            startActivity(intent)
            (requireActivity() as PillAddActivity).replacePillAddFormFragment()
        }
    }

    private fun navigateToHome() {
        binding.tvFinish.setOnClickListener {
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
            requireActivity().finish()
        }
    }
}
