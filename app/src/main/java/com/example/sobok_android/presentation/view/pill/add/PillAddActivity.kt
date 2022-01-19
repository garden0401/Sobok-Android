package com.example.sobok_android.presentation.view.pill.add

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.adapter.PillNameAdapter
import com.example.sobok_android.presentation.view.pill.add.adapter.PillTimeAdapter
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class PillAddActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add) {

    private val pillAddViewModel: PillAddViewModel by viewModel()
    private lateinit var pillNameAdapter: PillNameAdapter
    private lateinit var pillTimeAdapter: PillTimeAdapter


    val nameList = arrayListOf<String>()

    var periodString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvPillDateEveryday.isSelected = true
        binding.tvPillDateSpecificDay.isSelected = false
        binding.tvPillDateSpecificPeriod.isSelected = false
        binding.pillCycleMoreConstraintLayout.visibility = View.GONE
        binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE

        initPillNameAdapter()
        initPillTimeAdapter()

        showDialogPillPerson()
        addPillPerson()

        cycleEveryday()
        cycleSpecificDay()
        cycleSpecificPeriod()

        showDialogSpecificDay()
        showDialogSpecificPeriod()

        showDialogPillDate()
        showDialogPillTime()

        binding.clTop.setOnTouchListener { v, event ->
            binding.rcvPillName.clearFocus()
            return@setOnTouchListener false
        }

        binding.btnSaveConstraintLayout.setOnClickListener {
            pillAddViewModel.setIsComplete(true)
        }

        pillAddViewModel.isComplete.observe(this) {
            if (it) {
                // 데이터 값을 보내줘라.. 뷰모델로..........
                val intent = Intent(this, PillAddFinishActivity::class.java)
                startActivity(intent)
            }
        }

        /*
        private fun navigateToHome() {
        binding.tvFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
         */
    }
    private fun initPillNameAdapter() {
        pillNameAdapter = PillNameAdapter()
        binding.rcvPillName.adapter = pillNameAdapter
    }

    private fun initPillTimeAdapter() {
        pillTimeAdapter = PillTimeAdapter()
        binding.rcvPillTime.adapter = pillTimeAdapter
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
                this,
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
    }

    private fun showDialogSpecificPeriod() {
        binding.pillCycleSpecificMoreConstraintLayout.setOnClickListener {
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
    }

    private fun showDialogSpecificDay() {
        binding.pillCycleMoreConstraintLayout.setOnClickListener {
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
    }

    private fun cycleSpecificPeriod() {
        binding.tvPillDateSpecificPeriod.setOnClickListener {
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
            // 특정요일 버튼 선택
            if (!binding.tvPillDateSpecificDay.isSelected) {
                binding.tvPillDateSpecificDay.isSelected = true // this 전환
                binding.pillCycleMoreConstraintLayout.visibility = View.VISIBLE // 해당 피커 활성화
                binding.tvPillDateEveryday.isSelected = false // 첫번째 버튼 비활성화
                binding.tvPillDateSpecificPeriod.isSelected = false // 두번째 버튼 비활성화
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE // 피커 열린게 있으면 닫기
            }
        }
    }

    private fun addPillPerson() {
        binding.clAddPillName.setOnClickListener {
            pillNameAdapter.addItem("")
        }
    }



    private fun showDialogPillPerson() {
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
    }

    private fun cycleEveryday() {
        binding.tvPillDateEveryday.setOnClickListener {
            if (!binding.tvPillDateEveryday.isSelected) {
                binding.tvPillDateEveryday.isSelected = true // this 전환
                binding.tvPillDateSpecificDay.isSelected = false // 첫번째 버튼 비활성화
                binding.tvPillDateSpecificPeriod.isSelected = false // 두번째 버튼 비활성화
                binding.pillCycleMoreConstraintLayout.visibility = View.GONE
                binding.pillCycleSpecificMoreConstraintLayout.visibility = View.GONE // 피커 열린게 있으면 닫기
            }
        }
    }
}