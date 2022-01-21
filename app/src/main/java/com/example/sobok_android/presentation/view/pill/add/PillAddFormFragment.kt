package com.example.sobok_android.presentation.view.pill.add

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.Toast
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentPillAddFormBinding
import com.example.sobok_android.domain.model.pill.pilladd.PillListData
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.MainActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillListAdapter
import com.example.sobok_android.presentation.view.pill.add.adapter.PillNameAdapter
import com.example.sobok_android.presentation.view.pill.add.adapter.PillTimeAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.presentation.view.share.request.ShareRequestActivity
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*


class PillAddFormFragment :
    BindingFragment<FragmentPillAddFormBinding>(R.layout.fragment_pill_add_form) {

    private val pillAddViewModel: PillAddViewModel by sharedViewModel()
    private lateinit var pillNameAdapter: PillNameAdapter
    private lateinit var pillTimeAdapter: PillTimeAdapter
    private lateinit var pillListAdapter: PillListAdapter
    private lateinit var pillBottomSheetDialogFragment: PillAddBottomSheetFragment

    private val _pillList = mutableListOf<PillListData.PillInfo>()
    var pillList: List<PillListData.PillInfo> = _pillList

    private var fillPillName: Boolean = false
    private var fillPillDate: Boolean = false
    private var fillPillCycle: Boolean = false
    private var selectPillCycle: Int = 0
    private var setSpecificDay: Boolean = false
    private var setSpecificPeriod: Boolean = false

    var nameList = listOf<String>()


    var periodString = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPillDateEveryday.isSelected = true
        binding.tvPillDateSpecificDay.isSelected = false
        binding.tvPillDateSpecificPeriod.isSelected = false
        binding.pillCycleMoreConstraintLayout.visibility = View.GONE
        binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE

        // pillPerson 값 넣어주기

        observePillCount()
        initPillNameAdapter()
        initPillTimeAdapter()
        initPillListAdapter()

        showDialogPillPerson()
        addPillPerson()

        cycleEveryday()
        cycleSpecificDay()
        cycleSpecificPeriod()

        showDialogSpecificDay()
        showDialogSpecificPeriod()

        showDialogPillDate()
        showDialogPillTime()

        var _isMyPill: Boolean = false
        var _canAddPill: Boolean = true
        val intent = Intent(requireContext(), PillAddFinishFragment::class.java)

        binding.clTop.setOnTouchListener { v, event ->
            binding.rcvPillName.clearFocus()
            return@setOnTouchListener false
        }

        binding.tvFinish.setOnClickListener {
            fillPillName = (pillNameAdapter.isFillPillName)
            fillPillCycle = checkFillPillCycle()

            nameList = pillNameAdapter.realPillNameList
            Log.d("nameListtttttttttttt", "$nameList")


            Log.d("pillLIstttttttttt", "$_pillList")



            if (fillPillName && fillPillDate && fillPillCycle && pillTimeAdapter.itemCount > 0) {

                for (i in 0 until nameList.size) {
                    _pillList.add(
                        PillListData.PillInfo(
                            nameList[i],
                            false,
                            "red",
                            "start",
                            "end",
                            "cycle",
                            "day",
                            "specific",
                            timeList = pillTimeAdapter.pillTimeList
                        )
                    )
                }
                pillAddViewModel.setPillList(_pillList)
                Log.d("프래그먼트에서 리스트를 뷰모델에 저장합니다", "${pillAddViewModel.pillList}")
                // 다음으로 이동!!!!!!!!!!!!!!!
                val pillAddActivity= (activity as PillAddActivity)
                pillAddActivity.replacePillAddFinishFragment()


            } else {
                Toast.makeText(requireContext(), "약에 대한 정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        navigateToHome()
    }

    private fun observePillCount() {
        if (pillAddViewModel.isMyPill) {
            binding.ivPillPersonMore.visibility = View.GONE
            binding.clPillPerson.isClickable = false
        }
        if (pillAddViewModel.canAddPill) {
            Log.d("Add Activity1", "${pillAddViewModel.canAddPill}")
            binding.wrapScroll.visibility = View.VISIBLE
            binding.clCannotAddPill.visibility = View.GONE
        } else {
            Log.d("Add Activity2", "${pillAddViewModel.canAddPill}")
            binding.wrapScroll.visibility = View.GONE
            binding.clCannotAddPill.visibility = View.VISIBLE
        }
    }

    private fun initPillListAdapter() {
        pillListAdapter = PillListAdapter()

    }

    private fun initPillNameAdapter() {
        pillNameAdapter = PillNameAdapter()
        binding.rcvPillName.adapter = pillNameAdapter
    }

    private fun initPillTimeAdapter() {
        pillTimeAdapter = PillTimeAdapter()
        binding.rcvPillTime.adapter = pillTimeAdapter

        pillNameAdapter.realPillNameList.add("null")
        pillNameAdapter.pillNameList = pillNameAdapter.realPillNameList
    }

    private fun navigateToHome() {
        binding.ivCancel.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun checkFillPillCycle(): Boolean {
        if (selectPillCycle == 0) return true
        if (selectPillCycle == 1 && setSpecificDay) return true
        if (selectPillCycle == 2 && setSpecificPeriod) return true
        return false
    }

    private fun showDialogPillTime() {
        binding.clAddPillTime.setOnClickListener {
            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                pillTimeAdapter.makeText(SimpleDateFormat("HH:mm").format(cal.time))
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

    private fun showDialogPillDate() {
        binding.clPillDate.setOnClickListener {
            // 특정간격 피커
            val builder = MaterialDatePicker.Builder.dateRangePicker()
            val picker = builder.build()
            picker.show(parentFragmentManager, picker.toString())
            picker.addOnNegativeButtonClickListener { picker.dismiss() }
            picker.addOnPositiveButtonClickListener {
                val startDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.first)
                val endDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.second)
                binding.tvPillDate.text = "$startDate ~ $endDate"
                fillPillDate = true
            }
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

            period_string_list.setOnValueChangedListener(OnValueChangeListener { picker, oldVal, newVal ->
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
            })

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
            val arrayColors = arrayOf("월", "화", "수", "목", "금", "토", "일")
            val arrayChecked = booleanArrayOf(false, false, false, false, false, false, false)
            val builder = AlertDialog.Builder(requireContext())

            builder.setMultiChoiceItems(arrayColors, arrayChecked) { _, which, isChecked ->
                arrayChecked[which] = isChecked
            }

            binding.tvPillAddSpecificDay.text = ""

            builder.setPositiveButton("OK") { _, _ ->
                for (i in arrayColors.indices) {
                    val checked = arrayChecked[i]
                    if (checked) {
                        binding.tvPillAddSpecificDay.text =
                            "${binding.tvPillAddSpecificDay.text} ${arrayColors[i]}"
                    }
                }
            }
            setSpecificDay = true
            dialog = builder.create()
            dialog.show()
        }
    }

    private fun cycleSpecificPeriod() {

        binding.tvPillDateSpecificPeriod.setOnClickListener {
            selectPillCycle = 2
            // 특정간격 버튼 선택
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
            // 특정요일 버튼 선택
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

    private fun addPillPerson() {

        binding.clAddPillName.setOnClickListener {
            if (pillNameAdapter.itemCount < 5) {
                pillNameAdapter.realPillNameList.add("null")
                pillNameAdapter.pillNameList = pillNameAdapter.realPillNameList
            }
        }
    }

    private fun showDialogPillPerson() {
        binding.clPillPerson.setOnClickListener {
            lateinit var dialog: AlertDialog
            val pillPersonArray = arrayOf("엄마", "수현언니", "정원언니", "안드짱") //  이비분
            val builder = AlertDialog.Builder(requireContext())
            builder.setSingleChoiceItems(pillPersonArray, -1) { _, which ->
                val name = pillPersonArray[which]
                binding.tvPillAddNameDialog.text = name
                binding.tvSelectedPillPerson.text = name + "에게 전송할 약이에요"
                dialog.dismiss()
            }
            dialog = builder.create()
            dialog.show()
        }
    }

    private fun cycleEveryday() {
        binding.tvPillDateEveryday.setOnClickListener {
            selectPillCycle = 0
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