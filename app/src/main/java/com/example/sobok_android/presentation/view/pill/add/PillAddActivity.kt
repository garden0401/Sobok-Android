package com.example.sobok_android.presentation.view.pill.add

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class PillAddActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add) {

    private val pillAddViewModel : PillAddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val everyday = binding.tvPillDateEveryday
        val specificDay = binding.tvPillDateSpecificDay
        val specificDayPicker = binding.pillCycleMoreConstraintLayout
        val specificPeriod = binding.tvPillDateSpecificPeriod
        val specificPeriodPicker = binding.pillCycleSpecificMoreConstraintLayout

        var periodString = ""

        everyday.isSelected = true
        specificDay.isSelected = false
        specificPeriod.isSelected = false
        specificDayPicker.visibility = View.GONE
        specificPeriodPicker.visibility = View.GONE

        binding.clPillPerson.setOnClickListener {
            lateinit var dialog: AlertDialog
            val array = arrayOf("나", "엄마", "수현언니", "정원언니", "안드짱")
            val builder = AlertDialog.Builder(this)
            builder.setSingleChoiceItems(array, -1) { _, which ->
                val name = array[which]
                binding.tvPillAddNameDialog.text = name
                binding.tvSelectedPillPerson.text = name + "가 먹을 약이에요"
                dialog.dismiss()
            }
            dialog = builder.create()
            dialog.show()
        }

        // 여기서 viewModel 함수 불러온다
        binding.clAddPillName.setOnClickListener {
            // 함께 먹는 약 추가..
            binding.rcvPillName
            // 리스트.add(todo)
        }


        everyday.setOnClickListener {
            if (!everyday.isSelected) {
                everyday.isSelected = true // this 전환
                specificDay.isSelected = false // 첫번째 버튼 비활성화
                specificPeriod.isSelected = false // 두번째 버튼 비활성화
                specificDayPicker.visibility = View.GONE
                specificPeriodPicker.visibility = View.GONE // 피커 열린게 있으면 닫기
            }
        }

        specificDay.setOnClickListener {
            // 특정요일 버튼 선택
            if (!specificDay.isSelected) {
                specificDay.isSelected = true // this 전환
                specificDayPicker.visibility = View.VISIBLE // 해당 피커 활성화
                everyday.isSelected = false // 첫번째 버튼 비활성화
                specificPeriod.isSelected = false // 두번째 버튼 비활성화
                specificPeriodPicker.visibility = View.GONE // 피커 열린게 있으면 닫기
            }

        }

        specificPeriod.setOnClickListener {
            // 특정간격 버튼 선택
            if (!specificPeriod.isSelected) {
                specificPeriod.isSelected = true // this 전환
                specificPeriodPicker.visibility = View.VISIBLE // 해당 피커 활성화
                everyday.isSelected = false // 첫번째 버튼 비활성화
                specificDay.isSelected = false // 두번째 버튼 비활성화
                specificDayPicker.visibility = View.GONE // 피커 열린게 있으면 닫기
            }
        }
        specificDayPicker.setOnClickListener {
            lateinit var dialog: AlertDialog
            val arrayColors = arrayOf("월", "화", "수", "목", "금", "토", "일")
            val arrayChecked = booleanArrayOf(false, false, false, false, false, false, false)
            val builder = AlertDialog.Builder(this)

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
            dialog = builder.create()
            dialog.show()
        }


        specificPeriodPicker.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val edialog: LayoutInflater = LayoutInflater.from(this)
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
                // (period_string_list.value.toString()).toString()
                periodString += cycleIntList[period_int_list.value]
                periodString += cycleStringList[period_string_list.value]
                binding.tvPillAddSpecificCycle.text = periodString
                dialog.dismiss()
                dialog.cancel()
            }
            dialog.setView(mView)
            dialog.create()
            dialog.show()
        }

        binding.clPillDate.setOnClickListener {
            // 특정간격 피커
            val builder = MaterialDatePicker.Builder.dateRangePicker()
            builder.setTitleText(title)
            //
            val picker = builder.build()
            picker.show(supportFragmentManager, picker.toString())
            picker.addOnNegativeButtonClickListener { picker.dismiss() }
            picker.addOnPositiveButtonClickListener {
                val startDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.first)
                val endDate = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault()).format(it.second)
                binding.tvPillDate.text = "$startDate ~ $endDate"
            }
        }

        // 약 복약시간 리사이클러뷰..의 삭제 추가

        binding.clAddPillTime.setOnClickListener {
            // 시간 추가
        }
    }
}