package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sobok_android.R
import com.example.sobok_android.databinding.FragmentHomeBinding
import com.example.sobok_android.presentation.base.BindingFragment
import com.example.sobok_android.presentation.view.calendar.viewmodel.CalendarViewModel
import com.example.sobok_android.presentation.view.home.viewmodel.HomeViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import com.example.sobok_android.util.DateTimeUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homePillListAllAdapter: HomePillListAllAdapter
    private val calendarViewModel: CalendarViewModel by viewModel()

    // 고차함수써보기
    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val mainViewModel: MainViewModel by sharedViewModel()

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        Log.d("onviewcreated", "도착")

        calendarViewModel.getCalendarList()

        initAdapter()
        observeIsEditClickEvent()

        binding.isHome = true // 뷰모델에 넣어야 함 -> 기본 true



        //calendarViewModel.postCurPageFirstDayCalendar(Calendar.getInstance(Locale.KOREA))

        //계산을 해서

        calendarViewModel.remoteDateList.observe(viewLifecycleOwner) {
            //서버에서 받아오는 순간
            //계산을 해야지
            Log.d("want////RemoteDateList@@@@@@@@@2", "${it}")
            calendarViewModel.postCurPageFirstDayCalendar(Calendar.getInstance(Locale.KOREA))
            Log.d("want////completeList@@@@@@@@@2", "${calendarViewModel.completeDateList}")
            binding.viewCalendar.setCompleteDateList(calendarViewModel.completeDateList)
        }

        binding.viewCalendar.sendCalendar.observe(viewLifecycleOwner) {
            calendarViewModel.postSendDate(DateTimeUtil.convertUSDateToDashFormatString(it.time))
            binding.viewCalendar.layoutCalendarTopBinding.tvCalendarTopSelectDate.text = DateTimeUtil.convertDateToMonthFormat(it.time)
        }

//        binding.viewCalendar.setSendDateGetter {
//            calendarViewModel.postSendDate(DateTimeUtil.convertUSDateToDashFormatString(it.time))
//        }

//        binding.viewCalendar.sendDate.observe(viewLifecycleOwner) {
//            //calendarViewModel.postSendDate(DateTimeUtil.convertUSDateToDashFormatString(it.time))
//            binding.viewCalendar.layoutCalendarTopBinding.tvCalendarTopSelectDate.text =  calendarViewModel.sendDate.value
//            Log.d("sleepy////sendDate-observe HomeFragment입니다", "${DateTimeUtil.convertUSDateToDashFormatString(it.time)}")
//            Log.d("서버통신코드를 여기 넣고 싶어요!!!!", "senddate${DateTimeUtil.convertUSDateToDashFormatString(it.time)}")
//            //Log.d("calednar서버 받으러 출발!", "ㄱㅇㅇㅇ")
//            //calendarViewModel.getCalendarList()
//
//        }

        calendarViewModel.sendDate.observe(viewLifecycleOwner) {
            binding.viewCalendar.layoutCalendarTopBinding.tvCalendarTopSelectDate.text =  calendarViewModel.sendDate.value
            calendarViewModel.getCalendarList()
        }


        binding.viewCalendar.selectDate.observe(viewLifecycleOwner) {
            calendarViewModel.postSelectDate(it)
            Log.d("please/viewCalendar", "${it}")
        }

        calendarViewModel.selectDate.observe(viewLifecycleOwner) {
           // binding.viewCalendar.layoutCalendarTopBinding.tvCalendarTopSelectDate.text =  DateTimeUtil.convertSimpleDetailDateFormatToSimpleDateFormat(it)
            Log.d("please/날짜observe", "${it}")
        }



        //월간 주간 처리
        calendarViewModel.postIsMonth(true) // 추후 수정

        binding.viewCalendar.layoutCalendarTopBinding.clCalendarTopOrder.setOnClickListener {
            calendarViewModel.postIsMonth(!calendarViewModel.isMonth.value!!)
        }

        calendarViewModel.isMonth.observe(viewLifecycleOwner) {
            Log.d("observeIsMonth", "$it")
            binding.viewCalendar.layoutCalendarTopBinding.isMonth = it
            binding.viewCalendar.setIsMonth(it)
            //calendarViewModel.getCalendarList()
        }

    }


    private fun initAdapter() {
        homePillListAllAdapter = HomePillListAllAdapter()

        binding.rvHomePillListAll.adapter = homePillListAllAdapter

        // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
        homePillListAllAdapter.setStickerClickListener {
            mainViewModel.setIsStickerClick(it)
            Log.d("lala", "success${it}")
        }



        homePillListAllAdapter.homePillListAll =
            listOf(
                HomePillListData(
                    "7시",
                    listOf(
                        HomePillListData.PillDetailData("홍삼", true, "1"),
                        HomePillListData.PillDetailData("홍상2", false, "2"),
                        HomePillListData.PillDetailData("홍상3", false, "3"),
                    )
                ),
                HomePillListData(
                    "12시",
                    listOf(
                        HomePillListData.PillDetailData("텐텐", false, "4"),
                        HomePillListData.PillDetailData("텐텐3", true, "5"),
                    )
                ),
                HomePillListData(
                    "18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false, "1"),
                        HomePillListData.PillDetailData("비타민2", false, "3"),
                        HomePillListData.PillDetailData("텐텐2", true, "4"),
                        HomePillListData.PillDetailData("비타민3", true, "2"),
                    )
                ),
                HomePillListData(
                    "18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false, "5"),
                        HomePillListData.PillDetailData("비타민3", true, "2"),
                    )
                ),
                HomePillListData(
                    "18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false, "1"),
                        HomePillListData.PillDetailData("비타민2", false, "3"),
                        HomePillListData.PillDetailData("비타민3", true, "2"),
                    )
                ),
                HomePillListData(
                    "18시",
                    listOf(
                        HomePillListData.PillDetailData("비타민", false, "4"),
                        HomePillListData.PillDetailData("비타민2", false, "5"),
                        HomePillListData.PillDetailData("비타민3", true, "2"),
                    )
                ),
            )
        // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
        binding.isEditText = true//-> 수정

        // 메인 약 리스트 수정버튼<->완료 버튼
        binding.tvBtnHomePillListEdit.setOnClickListener {
            Log.d("초기값" ,"${it.isSelected}") // false -> 수정
            it.isSelected = !it.isSelected // 수정인 상태에서 클릭을하면 true로 바뀜
            homeViewModel.setIsEditClick(it.isSelected) // 수정을 눌렀다! true
        }
    }

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private fun observeIsEditClickEvent() {
        homeViewModel.isEditClick.observe(this) {
            binding.isEditText = !it
            homePillListAllAdapter.setIsEdit(it)
            Log.d("observeEdit", "${it}")
        }


    }


}