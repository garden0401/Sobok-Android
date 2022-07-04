package com.example.sobok_android.presentation.view.pill.add


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.selection.SelectionState
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormDateBinding
import com.example.sobok_android.presentation.base.BindingFragment
import java.util.*

class PillAddFormDateFragment :
    BindingFragment<FragmentPillAddFormDateBinding>(R.layout.fragment_pill_add_form_date) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.isShowDaysOfWeekTitle = false
        binding.calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {

            val firstYear : Int = binding.calendarView.selectedDays[0].calendar.get(Calendar.YEAR)
            val firstDay : Int = binding.calendarView.selectedDays[0].calendar.get(Calendar.DAY_OF_MONTH)
            val firstMonth : Int = binding.calendarView.selectedDays[0].calendar.get(Calendar.MONTH) + 1
            val lastYear : Int = binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(Calendar.YEAR)
            val lastDay : Int = binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(Calendar.DAY_OF_MONTH)
            val lastMonth : Int = binding.calendarView.selectedDays[binding.calendarView.selectedDates.size - 1].calendar.get(Calendar.MONTH) + 1

            binding.tvPillDate.text = "$firstYear.$firstMonth.$firstDay - $lastYear.$lastMonth.$lastDay"
        })

        binding.tvNext.setOnClickListener {
            findNavController().navigate(R.id.pillAddFormNameFragment)
        }
    }
}