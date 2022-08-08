package com.example.sobok_android.presentation.view.pill.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormDateBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import java.util.*

class PillAddFormDateFragment :
    BindingFragment<FragmentPillAddFormDateBinding>(R.layout.fragment_pill_add_form_date) {

    private lateinit var callback: OnBackPressedCallback
    private val pillAddViewModel: PillAddViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.isShowDaysOfWeekTitle = false
        binding.calendarView.selectionManager = RangeSelectionManager(
            OnDaySelectedListener {
                val firstYear: Int =
                    binding.calendarView.selectedDays[0].calendar.get(Calendar.YEAR)
                val firstDay: Int =
                    binding.calendarView.selectedDays[0].calendar.get(Calendar.DAY_OF_MONTH)
                val firstMonth: Int =
                    binding.calendarView.selectedDays[0].calendar.get(Calendar.MONTH) + 1
                val lastYear: Int =
                    binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(
                        Calendar.YEAR
                    )
                val lastDay: Int =
                    binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(
                        Calendar.DAY_OF_MONTH
                    )
                val lastMonth: Int =
                    binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(
                        Calendar.MONTH
                    ) + 1

                binding.tvPillDate.text =
                    "$firstYear.$firstMonth.$firstDay - $lastYear.$lastMonth.$lastDay"

                val start = "$firstYear-$firstMonth-$firstDay"
                val end = "$lastYear-$lastMonth-$lastDay"

                pillAddViewModel.start.value = start
                pillAddViewModel.end.value = end
                Log.d("start ", "${pillAddViewModel.start.value}")
                Log.d("end", "${pillAddViewModel.end.value}")
            }
        )

        binding.tvNext.setOnClickListener {
            findNavController().navigate(R.id.pillAddFormNameFragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
    }

    fun gotoThird() {
        findNavController().navigate(R.id.action_pillAddFormDateFragment_to_pillAddFormNameFragment)
    }
}
