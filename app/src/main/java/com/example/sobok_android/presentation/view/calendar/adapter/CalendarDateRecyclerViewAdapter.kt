package com.example.sobok_android.presentation.view.calendar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.R
import com.example.sobok_android.data.model.response.calendar.ResCalendarData
import com.example.sobok_android.databinding.ItemCalendarDateBinding
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.util.DateTimeUtil
import java.util.*

class CalendarDateRecyclerViewAdapter : RecyclerView.Adapter<CalendarDateRecyclerViewAdapter.MonthlyViewHolder>() {

    private var isSetCalenar : Boolean = false
    private val saveList = mutableListOf<ResCalendarData.CalendarDate?>()

    // position 전역으로 가져가고 고차함수 빼서 selectedPosition 저장
    // viewHolder -> selectedPosition -> ??/
    private var newSelectedPosition : Int = 10 // 오늘날짜로 바꿔야 함
    private var oldSelectedPosition: Int = 10

    private var _isNext: Boolean = false
    private var currentMonthGetter : ((Boolean, Calendar) -> Unit)? = null
    fun setCurrentMonthGetter(listener: (Boolean, Calendar) -> Unit) {
        currentMonthGetter = listener
    }

    private var completeDateList = listOf<CalendarData.CalendarDate?>()

    fun setCompleteDateList(value: List<CalendarData.CalendarDate?>) {
        completeDateList = value
        notifyDataSetChanged()
    }


    private val _dateList = mutableListOf<ResCalendarData.CalendarDate?>()
    var dateList: List<ResCalendarData.CalendarDate?> = _dateList
        set(value) {
            //Log.d("point/dateList@@@@@@@@@@", "$value")
            _dateList.clear()
            if(isSetCalenar) {
                //Log.d("saveList", "${saveList}")
                val testList = mutableListOf<ResCalendarData.CalendarDate?>()
                var idx = 0
                val maxIdx = saveList.size - 1
                val calCulCalendar : Calendar = _comCalendar.clone() as Calendar
                var count = when(_isMonth) {
                    true -> 42
                    else -> 7
                }
                    //Log.d("calcalCalendar", "${calCulCalendar.time}")
                    repeat(count) {
                        if (calCulCalendar.get(Calendar.MONTH) != curMonth) { // 현재 월이 아니면
                            testList.add(null)
                        }else {
                            if(idx <= maxIdx) { //saveList[idx]!!.scheduleDate 괄호안
                                if(DateTimeUtil.convertDateToDayOfMonth(Date()) ==
                                    calCulCalendar.get(Calendar.DAY_OF_MONTH)){
                                    testList.add(saveList[idx++])
                                }else {
                                    testList.add(null)
                                }
                            }else {
                                testList.add(null)
                            }

                        }
                        calCulCalendar.add(Calendar.DAY_OF_MONTH, 1)
                    }
                    //Log.d("testList", "${testList}")
                    _dateList.addAll(testList)
                isSetCalenar = false
            }else {
                saveList.addAll(value)
            }
            notifyDataSetChanged()
            field = value
        }

    private var _isMonth = true
    var isMonth: Boolean = _isMonth
        set(value) {
            Log.d("point/isMonthSetting!!!!!!!!!!!!1", "${value}")
            _isMonth = value
            field = value
        }

    private var postSelectDate: ((String) -> Unit)? = null

    fun setPostSelectData(listener: (String) -> Unit) {
        postSelectDate = listener
    }

    private var _comCalendar: Calendar = Calendar.getInstance(Locale.KOREA)
    var comCalendar: Calendar = _comCalendar

    private var _calculCalendar :Calendar = Calendar.getInstance(Locale.KOREA)

    fun setMonthCalendar(value: Calendar) {
        Log.d("point/setMonthCalendar밖에서 1일로 만들어 줌!!!!!!-month", "${value.time}")
        _comCalendar = value.clone() as Calendar //TODO clone 추가
        _comCalendar.set(Calendar.DAY_OF_MONTH, 1) // 1월 1일로 만듬
        curMonth = _comCalendar.get(Calendar.MONTH)
        _isNext = false
        Log.d("check/맨 처음 날짜", "${_comCalendar.time}")
        if (_comCalendar.get(Calendar.DAY_OF_WEEK) == 1) // 일요일이면
            _comCalendar.add(Calendar.DAY_OF_MONTH, -6) // 현재 일에다가 -6을 해준다.... 11월 2x일
        else //일요일 아니야...악...2,3,4,5,6,7,1
            _comCalendar.add(Calendar.DAY_OF_MONTH, 2 - _comCalendar.get(Calendar.DAY_OF_WEEK))

        isSetCalenar = true
        dateList = listOf(null, null)
        //_calculCalendar.set(_comCalendar.get(Calendar.YEAR), _comCalendar.get(Calendar.MONTH), _comCalendar.get(Calendar.DAY_OF_WEEK))
    }

    fun setWeekCalendar(value: Calendar) {
        Log.d("point/setweekCalendar-week", "${value.time}")
        _comCalendar = value
        if (_comCalendar.get(Calendar.DAY_OF_WEEK) == 1) // 일요일이면
            _comCalendar.add(Calendar.DAY_OF_MONTH, -6) // 현재 일에다가 -6을 해준다.... 11월 2x일
        else //일요일 아니야...악...2,3,4,5,6,7,1
            _comCalendar.add(Calendar.DAY_OF_MONTH, 2 - _comCalendar.get(Calendar.DAY_OF_WEEK))
        Log.d("point/setweekCalendar-week", "${value.time}")

        isSetCalenar = true
        dateList = listOf(null)
    }

    private val nowCalendar = Calendar.getInstance(Locale.KOREA) //오늘 날짜
    var curMonth = comCalendar.get(Calendar.MONTH) // 현재 month

    //calendar util
    fun Calendar.isDaySame(calendar: Calendar): Boolean {
        return get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
                && get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyViewHolder {
        val binding: ItemCalendarDateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_calendar_date, parent, false
        )
        //Log.d("point/ㄲ아아앙", "onCreateViewHolder")
        return MonthlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonthlyViewHolder, position: Int) {
        //Log.d("point/dateList", "${_dateList}")
//        if(_isMonth) {
//            Log.d("point/onbindViewHolder-month", "$position")
//            holder.onBind(_monthDateList[position])
//        }else {
//            Log.d("point/onbindViewHolder-week", "$position")
//            holder.onBind(_weekDateList[position])
//        }
        //holder.onBind(_dateList[position], position)
        holder.onBind(completeDateList[position], position)
    }

    override fun getItemCount(): Int = completeDateList.size

    inner class MonthlyViewHolder(
        private val binding: ItemCalendarDateBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun onBind(item: CalendarData.CalendarDate?, position: Int) {
            Log.d("onBind", "다시 호출!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d("*********selectedPostion", "${oldSelectedPosition}")

            if(position == oldSelectedPosition) {
                // visible로 바꾸고... 근데 원래값을 작게만드는코드를 짜야함....
                //selectedPosition
                Log.d("oldePosition 맞음!!!!", "${position}")
                binding.isEmpty = false
                binding.isNone = false
                oldSelectedPosition = newSelectedPosition
//                        if (binding.isNone == true) {
//                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateNoneSelectTextStyle)
//                        } else {
//                            if (item != null) {
//                                if (item.isComplete == "done") {
//                                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneSelectTextStyle)
//                                    binding.ivCalendarDateCircle.setBackgroundResource(R.drawable.ic_date_done_select)
//                                } else if (item.isComplete == "doing") {
//                                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingSelectTextStyle)
//                                    binding.ivCalendarDateCircle.setBackgroundResource(R.drawable.ic_date_doing_select)
//                                }
//                            }
//                        }

            } else {
//                if (binding.isNone == true) {
//                    Log.d("check/none", "false")
//                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingTextStyle)
//                } else {
//                    if (item != null) {
//                        if (item.isComplete == "done") {
//                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneTextStyle)
//                            binding.ivCalendarDateCircle.setBackgroundResource(R.drawable.ic_date_done_not_select)
//                        } else if (item.isComplete == "doing") {
//                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingTextStyle)
//                            binding.ivCalendarDateCircle.setBackgroundResource(R.drawable.ic_date_doing_not_select)
//                        }
//                    }
//                }

            }

            //Log.d("check/날짜", "${_comCalendar.time}")
            if (_comCalendar.get(Calendar.MONTH) != curMonth) { // 현재 월이 아니면
                //Log.d("want//현재 월", "아님${position}")
                binding.isEmpty = true
                binding.isToday = false
                if(_comCalendar.get(Calendar.MONTH) > curMonth) {
                    _isNext = true
                }
            } else { // 현재 월이면
                binding.isEmpty = false
                binding.tvCalendarDate.text = _comCalendar.get(Calendar.DAY_OF_MONTH).toString()

                binding.date = DateTimeUtil.convertCalendarToSimpleDateDetailFormat(_comCalendar)
                //2022-01-01 토 22:06:55

                if (_comCalendar.isDaySame(nowCalendar)) { // 오늘이면
                    //Log.d("want//오늘", "맞음${position}")
                    binding.isNone = true
                    binding.isToday = true
                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarTodayTextStyle)
                } else { //오늘이 아니면
                    binding.isToday = false
                    if (item == null) {
                        //Log.d("want//오늘 아니고 현재월 인데", "null${position}")
                        binding.isNone = true
                    } else {
                        //Log.d("want//서버 데이터 있음", "여기야 여기${position}")
                        when (item.isComplete) {
                            "done" -> {
                                binding.isComplete = true
                                binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneTextStyle)
                            }
                            "doing" -> {
                            }
                            else -> {
                                binding.isNone = true
                            }
                        }
                    }




                    binding.clCalendarDate.setOnClickListener { // none일 때만!!!
                        when (binding.tvCalendarDate.isSelected) {
                            true -> {
                                binding.tvCalendarDate.isSelected = false
                                binding.ivCalendarDateCircle.isSelected = false
                                //Log.d("check/none", "${binding.ivCalendarDateCircle.isSelected}")
                                if (binding.isNone == true) {
                                    //Log.d("check/none", "false")
                                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingTextStyle)
                                } else {
                                    if (item != null) {
                                        if (item.isComplete == "done") {
                                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneTextStyle)
                                            binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_done_not_select)
                                        } else if (item.isComplete == "doing") {
                                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingTextStyle)
                                            binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_doing_not_select)
                                        }
                                    }
                                }

                            }
                            else -> {
                                binding.tvCalendarDate.isSelected = true
                                binding.ivCalendarDateCircle.isSelected = true

                                //notifyItemChanged(selectedPosition)
                                notifyItemChanged(oldSelectedPosition)
                                //oldSelectedPosition = position
                                newSelectedPosition = position

                                postSelectDate?.invoke(binding.date
                                        ?: DateTimeUtil.convertCalendarToSimpleDateDetailFormat(Calendar.getInstance(Locale.KOREA))
                                )

                                ////2022-01-01 토 22:06:55
                                if (binding.isNone == true) {
                                    binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateNoneSelectTextStyle)
                                } else {
                                    if (item != null) {
                                        if (item.isComplete == "done") {
                                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoneSelectTextStyle)
                                            binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_done_select)
                                        } else if (item.isComplete == "doing") {
                                            binding.tvCalendarDate.setTextAppearance(R.style.CalendarDateDoingSelectTextStyle)
                                            binding.ivCalendarDateCircle.setImageResource(R.drawable.ic_date_doing_select)
                                        }
                                    }
                                }
                            }
                        }
                        // 고차함수 만들고 호출하고 외부에서 setMonth를 다시 한다...! 1월 1일을 다시 보냄..!!
                        currentMonthGetter?.invoke(_isNext, _comCalendar)
                        // 전월의 1일을 계산해서 보냄...............
                        //31일이 42칸에 있으면 현재 월의 1일을 계싼해서 보냄
                        // boolean isNext -> true 이전달의 1일을 보내고 false -> 현재 달의 1일을 보내고
                            //선택한 날짜, 예날 선택그거만 notify
                        //notifyDataSetChanged()
                        //notifyItemChanged(selectedPosition)

                    }

                }
            }
            _comCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }
}
