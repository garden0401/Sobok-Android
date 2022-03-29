package com.example.sobok_android.presentation.view.pill.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormDateBinding
import com.example.sobok_android.presentation.base.BindingFragment

class PillAddFormDateFragment : BindingFragment<FragmentPillAddFormDateBinding>(R.layout.fragment_pill_add_form_date) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        binding.calendarView.isShowDaysOfWeekTitle = false
        binding.calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
            Log.e(" CALENDAR ", "Selected Dates : " + binding.calendarView.selectedDates.size)
            if (binding.calendarView.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.e(" CALENDAR ", "Selected Days : " + binding.calendarView.selectedDays)
        })
         */
    }
}