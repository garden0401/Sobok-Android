package com.example.sobok_android.presentation.view.pill.add

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormBinding
import com.example.sobok_android.domain.model.pill.pilladd.PillInfo
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.pill.add.adapter.PillListAdapter
import com.example.sobok_android.presentation.view.pill.add.adapter.PillTimeAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.util.DateTimeUtil
import com.example.sobok_android.util.DateTimeUtil.convertPillListStringToKoreaTime
import java.text.SimpleDateFormat
import java.util.*

class PillAddFormFragment :
    BindingFragment<FragmentPillAddFormBinding>(R.layout.fragment_pill_add_form) {

    private val pillAddViewModel: PillAddViewModel by activityViewModels()

    private lateinit var pillTimeAdapter: PillTimeAdapter
    private lateinit var pillListAdapter: PillListAdapter

    private val _pillList = mutableListOf<PillInfo>()
    var pillList: List<PillInfo> = _pillList

    private var fillPillName: Boolean = false
    private var fillPillDate: Boolean = false
    private var fillPillCycle: Boolean = false
    private var selectPillCycle: Int = 0
    private var setSpecificDay: Boolean = false
    private var setSpecificPeriod: Boolean = false

    var sendStartDate: String = ""
    var sendEndDate: String = ""
    var sendCycle: Int = 1
    var sendDay: String? = null
    var sendSpecific: String? = null

    var nameList = listOf<String>()

    var periodString = ""
    private var serverPeriodString = MutableLiveData<String>()

    private var pillDays = MutableLiveData<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = pillAddViewModel
            cycleFragment = this@PillAddFormFragment
        }

        observeNavigateData()

        // 내약추가일때 판단 변수
        // pillAddViewModel.pillAddNavigateData.value?.isMyPill

        //_pillTimeList = pillAddViewModel.pillTimeList

        Log.d("mycount ", "${pillAddViewModel.getPillAddNavigetData()?.pillCount}")

        binding.tvPillDateEveryday.isSelected = true
        binding.tvPillDateSpecificDay.isSelected = false
        binding.tvPillDateSpecificPeriod.isSelected = false
        binding.pillCycleMoreConstraintLayout.visibility = View.GONE
        binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE

        initPillTimeAdapter()
        initPillListAdapter()

        setCycle()
        cycleEveryday()
        cycleSpecificDay()
        cycleSpecificPeriod()

        showDialogSpecificDay()
        showDialogSpecificPeriod()
        showDialogPillTime()

        navigateToHome()
    }

    private fun setCycle() {
        val cycle = pillAddViewModel.getCycle()
        when (cycle) {
            1 -> {
                binding.tvPillDateEveryday.isSelected = true
                binding.tvPillDateSpecificDay.isSelected = false
                binding.tvPillDateSpecificPeriod.isSelected = false
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE

            }
            2 -> {
                binding.tvPillDateEveryday.isSelected = false
                binding.tvPillDateSpecificDay.isSelected = true
                binding.tvPillDateSpecificPeriod.isSelected = false
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE
                binding.pillCycleMoreConstraintLayout.visibility = View.VISIBLE
                binding.tvPillAddSpecificDay.text =
                    pillAddViewModel.pillCycleSpecificDaysString.value
            }
            3 -> {
                binding.tvPillDateEveryday.isSelected = false
                binding.tvPillDateSpecificDay.isSelected = false
                binding.tvPillDateSpecificPeriod.isSelected = true
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.VISIBLE
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE
                binding.tvPillAddSpecificCycle.text = pillAddViewModel.getPillPeriod()
            }
            else -> {
                binding.tvPillDateEveryday.isSelected = true
                binding.tvPillDateSpecificDay.isSelected = false
                binding.tvPillDateSpecificPeriod.isSelected = false
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE
            }
        }
    }

    private fun navigateToDate() {
        binding.tvNext.setOnClickListener {
            /* 약 전송
            fillPillName = (pillNameAdapter.isFillPillName)
             */
            fillPillCycle = checkFillPillCycle()
            /* 약 전송
            nameList = pillNameAdapter.realPillNameList
             */

            if (fillPillName && fillPillDate && fillPillCycle && pillTimeAdapter.itemCount > 0) {
                sendStartDate = DateTimeUtil.convertToPillAddFinishDate(sendStartDate)
                sendEndDate = DateTimeUtil.convertToPillAddFinishDate(sendEndDate)
                pillAddViewModel.setPillList(_pillList)
                findNavController().navigate(R.id.action_pillAddForm_to_pillAddFormDate)
            } else {
                Toast.makeText(requireContext(), "약에 대한 정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                // 원래 이거 두줄 빼야됩니다
                val pillAddActivity = (activity as PillAddActivity)
                // pillAddActivity.replacePillAddDateFragment()
                findNavController().navigate(R.id.pillAddFormDateFragment)
            }
        }
    }

    fun gotoSecond() {
        findNavController().navigate(R.id.action_pillAddForm_to_pillAddFormDate)
    }

    private fun observeNavigateData() {
        pillAddViewModel.pillAddNavigateData.observe(viewLifecycleOwner) {
            if (it.canAddPill) {
                binding.wrapScroll.visibility = View.VISIBLE
                binding.clCannotAddPill.visibility = View.GONE
            } else {
                binding.wrapScroll.visibility = View.GONE
                binding.clCannotAddPill.visibility = View.VISIBLE
            }
        }
    }

    private fun initPillListAdapter() {
        pillListAdapter = PillListAdapter()
    }

    private fun initPillTimeAdapter() {
        pillTimeAdapter = PillTimeAdapter(::deleteTime)
        Log.d("time list..", "${pillAddViewModel.pillTimeList}")
        pillTimeAdapter.submitList(pillAddViewModel.pillTimeList)
        binding.rcvPillTime.adapter = pillTimeAdapter

    }

    private fun deleteTime(time: String) {
        pillAddViewModel.deleteTime(time)
        pillTimeAdapter.submitList(pillAddViewModel.pillTimeList)
    }

    private fun navigateToHome() {
        binding.ivCancel.setOnClickListener {
            requireActivity().finish()
        }
    }

    fun checkFillPillCycle(): Boolean {
        if (selectPillCycle == 0) {
            sendCycle = 1
            sendDay = "월, 화, 수, 목, 금, 토, 일"
            return true
        }
        if (selectPillCycle == 1 && setSpecificDay) {
            sendCycle = 2
            return true
        }
        if (selectPillCycle == 2 && setSpecificPeriod) {
            sendCycle = 3
            sendSpecific = binding.tvPillAddSpecificCycle.toString()
            return true
        }
        return false
    }

    private fun showDialogPillTime() {
        binding.clAddPillTime.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                val string = (SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time))
                pillAddViewModel.addTime(convertPillListStringToKoreaTime(string))
                pillTimeAdapter.submitList(pillAddViewModel.pillTimeList)
                pillTimeAdapter.notifyDataSetChanged()
            }

            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun showDialogSpecificPeriod() {
        binding.pillCycleSpecificMoreConstraintLayout.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val edialog: LayoutInflater = LayoutInflater.from(requireContext())
            val mView: View = edialog.inflate(R.layout.number_picker_dialog, null)

            periodString = ""

            val period_int_list: NumberPicker = mView.findViewById(R.id.np_period_int)
            val period_string_list: NumberPicker = mView.findViewById(R.id.np_period_string)
            val ok: Button = mView.findViewById(R.id.tv_ok)
            val cancel: Button = mView.findViewById(R.id.tv_cancel)

            period_int_list.wrapSelectorWheel = false
            period_string_list.wrapSelectorWheel = false

            period_int_list.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            period_string_list.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            val cycleIntList = mutableListOf<String>()
            val cycleInt2List = mutableListOf<String>()
            val cycleInt3List = mutableListOf<String>()
            val cycleStringList = arrayOf<String>("일에 한 번", "주에 한 번", "달에 한 번")
            val sCycleStringList = arrayOf<String>("day", "week", "month")

            repeat(365) {
                cycleIntList.add((it + 1).toString())
            }

            repeat(52) {
                cycleInt2List.add((it + 1).toString())
            }

            repeat(12) {
                cycleInt3List.add((it + 1).toString())
            }

            period_int_list.minValue = 0
            period_string_list.minValue = 0

            period_string_list.maxValue = cycleStringList.size - 1

            period_int_list.wrapSelectorWheel = false
            period_string_list.wrapSelectorWheel = false
            period_string_list.displayedValues = cycleStringList

            period_string_list.setOnValueChangedListener(
                OnValueChangeListener { picker, oldVal, newVal ->
                    if (newVal == 0) {
                        period_int_list.value = 0
                        period_int_list.displayedValues = cycleIntList.toTypedArray()
                        period_int_list.maxValue = cycleIntList.size - 1
                    }
                    if (newVal == 1) {
                        period_int_list.value = 0
                        period_int_list.displayedValues = cycleInt2List.toTypedArray()
                        period_int_list.maxValue = cycleInt2List.size - 1
                    }
                    if (newVal == 2) {
                        period_int_list.value = 0
                        period_int_list.displayedValues = cycleInt3List.toTypedArray()
                        period_int_list.maxValue = cycleInt3List.size - 1
                    }
                }
            )

            period_int_list.maxValue = cycleIntList.size - 1
            period_int_list.displayedValues = cycleIntList.toTypedArray()

            cancel.setOnClickListener {
                dialog.dismiss()
                dialog.cancel()
            }
            ok.setOnClickListener {
                periodString += cycleIntList[period_int_list.value]
                periodString += cycleStringList[period_string_list.value]
                binding.tvPillAddSpecificCycle.text = periodString

                // period_int_list.value = 숫자
                // period_string_list.value = 일에 한번 주에 한번 달에 한번
                val string =
                    cycleIntList[period_int_list.value] + sCycleStringList[period_string_list.value]

                serverPeriodString.value = string
                pillAddViewModel.setPillPeriod(serverPeriodString)

                // 복약기간 true
                setSpecificPeriod = true

                dialog.dismiss()
                dialog.cancel()
            }
            dialog.setView(mView)
            dialog.create()
            dialog.show()
        }
    }

    private fun showDialogSpecificDay() {
        binding.pillCycleMoreConstraintLayout.setOnClickListener {
            lateinit var dialog: AlertDialog
            val arrayDays = arrayOf("월", "화", "수", "목", "금", "토", "일")
            val arrayChecked = booleanArrayOf(false, false, false, false, false, false, false)
            val builder = AlertDialog.Builder(requireContext())

            builder.setMultiChoiceItems(arrayDays, arrayChecked) { _, which, isChecked ->
                arrayChecked[which] = isChecked
            }

            binding.tvPillAddSpecificDay.text = ""
            var days = ""
            builder.setPositiveButton("OK") { _, _ ->
                for (i in arrayDays.indices) {
                    val checked = arrayChecked[i]
                    if (checked) {
                        pillAddViewModel.addPillCycleSpecificDay(arrayDays[i])
                        days += arrayDays[i] + ", "
                    }
                }
                val daysLength:Int = days.length
                days = days.substring(0, daysLength-1)
                binding.tvPillAddSpecificDay.text = days
                pillAddViewModel.setPillDays(days)
                sendSpecific = days
            }
            val string = binding.tvPillAddSpecificDay.toString()
            pillDays.value = string
            sendDay = binding.tvPillAddSpecificDay.toString()
            setSpecificDay = true

            dialog = builder.create()
            dialog.show()
        }
    }

    private fun cycleSpecificPeriod() {
        binding.tvPillDateSpecificPeriod.setOnClickListener {
            selectPillCycle = 2
            // 특정간격 버튼 선택
            pillAddViewModel.setCycle(3)
            if (!binding.tvPillDateSpecificPeriod.isSelected) {
                binding.tvPillDateSpecificPeriod.isSelected = true // this 전환
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.VISIBLE // 해당 피커 활성화
                binding.tvPillDateEveryday.isSelected = false // 첫번째 버튼 비활성화
                binding.tvPillDateSpecificDay.isSelected = false // 두번째 버튼 비활성화
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE // 피커 열린게 있으면 닫기
            }
        }
    }

    private fun cycleSpecificDay() {
        binding.tvPillDateSpecificDay.setOnClickListener {
            selectPillCycle = 1
            pillAddViewModel.setCycle(2)
            if (!binding.tvPillDateSpecificDay.isSelected) {
                binding.tvPillDateSpecificDay.isSelected = true // this 전환
                binding.pillCycleMoreConstraintLayout.visibility = View.VISIBLE // 해당 피커 활성화
                binding.tvPillDateEveryday.isSelected = false // 첫번째 버튼 비활성화
                binding.tvPillDateSpecificPeriod.isSelected = false // 두번째 버튼 비활성화
                binding.pillCycleSpecificMoreConstraintLayout.visibility =
                    View.GONE // 피커 열린게 있으면 닫기
            }
        }
    }

    private fun cycleEveryday() {
        binding.tvPillDateEveryday.setOnClickListener {
            selectPillCycle = 0
            pillAddViewModel.setCycle(1)

            if (!binding.tvPillDateEveryday.isSelected) {
                binding.tvPillDateEveryday.isSelected = true // this 전환
                binding.tvPillDateSpecificDay.isSelected = false // 첫번째 버튼 비활성화
                binding.tvPillDateSpecificPeriod.isSelected = false // 두번째 버튼 비활성화
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE
                binding.pillCycleSpecificMoreConstraintLayout.visibility =
                    View.GONE // 피커 열린게 있으면 닫기
            }
        }
    }
}
