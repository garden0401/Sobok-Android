package com.example.sobok_android.presentation.view.calendar.adapter

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sobok_android.domain.model.calendar.CalendarData
import com.example.sobok_android.presentation.view.calendar.CalendarView.Companion.FIRST_POSITION
import com.example.sobok_android.presentation.view.calendar.MonthView
import com.example.sobok_android.util.DateTimeUtil
import java.text.SimpleDateFormat
import java.util.*

class CalendarMonthViewPagerAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<CalendarMonthViewPagerAdapter.CalendarViewHolder>() {
    //리스너 달기
    private var postSelectDate: ((String) -> Unit)? = null

    fun setPostSelectData(listener: (String) -> Unit) {
        postSelectDate = listener
    }

    private var _isMonth: Boolean = true
    fun setIsMonth(value: Boolean) {
        _isMonth = value
    }

//    private var start: Date =
//    override fun createFragment(position: Int): CalendarFragment {
//        val millis = getItemId(position) return CalendarFragment.newInstance(millis)
//    }
//    override fun getItemId(position: Int): Long = DateTime(start).plusMonths(position - START_POSITION).millis
//    override fun containsItem(itemId: Long): Boolean {
//        val date = DateTime(itemId) return date.dayOfMonth == 1 && date.millisOfDay == 0
//    }

//    override fun getItemId(position: Int): Long {
//        val id = Calendar.getInstance(Locale.KOREA).add(Calendar.MONTH, position - 5)
//        return Calendar.getInstance(Locale.KOREA).timeInMillis
//    }



    private val completeDateList = MutableLiveData<List<CalendarData.CalendarDate?>>()

    fun setCompleteDateList(value: List<CalendarData.CalendarDate?>) {
        completeDateList.postValue(value)
    }

    var sendCalendar : Calendar = Calendar.getInstance(Locale.KOREA)

    private var sendDateGetter: ((Calendar) -> Unit)? = null
    fun setSendDateGetter(listener: (Calendar) -> Unit) {
        sendDateGetter = listener
    }

    private var isSetPosition : Boolean = false


    var currentPosition : Int = 5

    fun setCurrentPostion(value: Int) {
        Log.d("currentPostion-setting", "$${value}")
        isSetPosition = true
        currentPosition = value
        notifyItemChanged(currentPosition)  // ?????????
    }


    override fun getItemCount(): Int = MAX_ITEM_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val monthView = MonthView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return CalendarViewHolder(monthView)
    }

    override fun onBindViewHolder(
        holder: CalendarViewHolder,
        position: Int
    ) {
        Log.d("check/position", "${position}")
        //holder.setIsRecyclable(false)
        holder.bind(position)
    }

//    init {
//        setuser(true)
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }

    inner class CalendarViewHolder(private val view: MonthView) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
//            view.setCalendarGetter {
//                return@setCalendarGetter Calendar.getInstance(Locale.KOREA).apply {
//                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                    add(Calendar.MONTH, position - FIRST_POSITION) // 좌우 스와이프 시 월 +-1 계산
//                }
//            }

//            view.setWeekCalendarGetter {
//                return@setWeekCalendarGetter Calendar.getInstance(Locale.KOREA).apply {
//                    add(Calendar.WEEK_OF_MONTH, position - FIRST_POSITION)
//                }
//            }

            //dateData.observe(lifecycleOwner) {

            //sendCalendar =  //이거 연결된거 같아서 한번만 풀어줘 그 복붙 말고 clone으로 처리ㅣ해서 지금 한번 돌 떄 3번ㅅ씩 더해지는 듯! 수정 부탁

            //Log.d("sendDate-MonthViewPager가 움직이는 중", "${DateTimeUtil
               // .convertUSDateToDashFormatString(sendCalendar.time)}")

           // Toast.makeText(view.context, "${currentPosition}", Toast.LENGTH_SHORT).show()
            view.dateRecyclerView.adapter = CalendarDateRecyclerViewAdapter().apply {
                isMonth = _isMonth

                //if(isSetPosition) {
                    Log.d("isrealPosition", "${position}")
                    sendCalendar = Calendar.getInstance(Locale.KOREA).apply {
                        set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
                        add(Calendar.MONTH, currentPosition - FIRST_POSITION) // 좌우 스와이프 시 월 +-1 계산
                    }
                    setMonthCalendar(sendCalendar)
                    isSetPosition = false
                //}

                completeDateList.observe(lifecycleOwner) {
                    Log.d("MonthVpAdpater에서 completeList-> dateRecyclerView로 보냄", "${it}")
                    this.setCompleteDateList(completeDateList.value ?: listOf())
                }

//                Log.d("sendDate", "${Calendar.getInstance(Locale.KOREA)}")
//                sendDateGetter?.invoke(Calendar.getInstance(Locale.KOREA).apply {
//                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                    add(Calendar.MONTH, position - FIRST_POSITION) // 좌우 스와이프 시 월 +-1 계산
//                })

                Log.d("point/dateRecyclerViewMonth", "$_isMonth")
//                setCurrentMonthGetter { isNext, comCalendar ->
//                    setMonthCalendar(
//                        comCalendar.apply {
//                            when (isNext) {
//                                true -> {
//                                    add(Calendar.MONTH, -1) // 이전 달로 만들고
//                                    set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                                }
//                                else -> {
//          //                          set(Calendar.DAY_OF_MONTH, 1) // 현재 해당 일을 1일로 초기화하기
//                                    //add(Calendar.MONTH, ) // 좌우 스와이프 시 월 +-1 계산
//                                }
//                            }
//                        }
//                    )
//                }
                setPostSelectData {
                    postSelectDate?.invoke(it)
                    Log.d("please/CalendarViewPagerAdpater", "$it")
                }
            }

//            completeDateList.observe(lifecycleOwner) {
//                view.setCompleteDateGetter {
//                    return@setCompleteDateGetter it ?: listOf(null)
//                }
//            }


        }
    }

    companion object {
        //const val MAX_ITEM_COUNT = Int.MAX_VALUE
        const val MAX_ITEM_COUNT = 10
    }
}