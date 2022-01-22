package com.example.sobok_android.presentation.view.home

import android.os.Bundle
import android.view.View
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

    private var sendCurMonth = 0

    // 고차함수써보기
    // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
    private val mainViewModel: MainViewModel by sharedViewModel()

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarViewModel.getCalendarList()

        observeIsHome()
        initAdapter()
        //observeIsEditClickEvent()
        //observeSelectedDate()
        //observeHomePillList()

        calendarViewModel.remoteDateList.observe(viewLifecycleOwner) {
            //calendarViewModel.postCurPageFirstDayCalendar(Calendar.getInstance(Locale.KOREA)) //TODO ???????
            //TODO: setCompletedDateList는 계산이 끝나고 나서 즉 observe하고 viewClanedar로 보내야 함
            calendarViewModel.setDynamicCalendar(calendarViewModel.sendDate.value!!.clone() as Calendar)
        }

        calendarViewModel.completeDateList.observe(viewLifecycleOwner) {
            binding.viewCalendar.setCompleteDateList(calendarViewModel.completeDateList.value!!)
        }

        binding.viewCalendar.sendCalendar.observe(viewLifecycleOwner) {
            if ((it.get(Calendar.MONTH) + 1) != sendCurMonth) {
                sendCurMonth = it.get(Calendar.MONTH) + 1
                calendarViewModel.postSendDate(it)
                binding.viewCalendar.layoutCalendarTopBinding.tvCalendarTopSelectDate.text =
                    DateTimeUtil.convertDateToMonthFormat(it.time)
                calendarViewModel.getCalendarList()
            }
        }

        binding.viewCalendar.testDate.observe(viewLifecycleOwner) {
            homeViewModel.setSelectedDate(it)
        }

        binding.viewCalendar.selectDate.observe(viewLifecycleOwner) {
            calendarViewModel.postSelectDate(it)
        }

//        calendarViewModel.selectDate.observe(viewLifecycleOwner) {
//            Log.d("please/날짜observe", "${it}")
//        }

        //월간 주간 처리
        calendarViewModel.postIsMonth(true) // 추후 수정

        binding.viewCalendar.layoutCalendarTopBinding.clCalendarTopOrder.setOnClickListener {
            calendarViewModel.postIsMonth(!calendarViewModel.isMonth.value!!)
        }

        calendarViewModel.isMonth.observe(viewLifecycleOwner) {
            binding.viewCalendar.layoutCalendarTopBinding.isMonth = it
            binding.viewCalendar.setIsMonth(it)
        }

    }

    private fun observeIsHome() {
        mainViewModel.isHome.observe(viewLifecycleOwner) {
            binding.isHome = it
            homePillListAllAdapter.setIsHome(it)
            if (it) {
                //TODO: UserName 연결
                binding.userName = "소복"
                observeIsEditClickEvent()
                setTvBtnHomePillListEditClickListener()
                observeHomePillList()

                //TODO: CalendarViewModel로 변경 예정
                homeViewModel.selectedDate.observe(viewLifecycleOwner) {
                    getHomePillList()
                }
            } else {
                //TODO: 클릭한 친구 ID 보내기
                observeSelectedMemberName()
                observeSharePillList()

                //TODO: CalendarViewModel로 변경 예정
                homeViewModel.selectedDate.observe(viewLifecycleOwner) {
                    getSharePillList()
                }
            }
        }

    }

    private fun observeSelectedMemberName() {
        mainViewModel.selectedMemberName.observe(viewLifecycleOwner) {
            binding.userName = it
        }
    }


    private fun initAdapter() {
        //homePillListAllAdapter.homePillListAll =
        // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
        binding.isEditText = true//-> 수정
        binding.isEmpty = false
        homePillListAllAdapter = HomePillListAllAdapter()
        binding.rvHomePillListAll.adapter = homePillListAllAdapter

        setStickerClickListener()//TODO: 홈인지 공유인지 구분해서 바텀시트가 달라져야 함!
    }

    private fun setTvBtnHomePillListEditClickListener() {
        // 메인 약 리스트 수정버튼<->완료 버튼
        binding.tvBtnHomePillListEdit.setOnClickListener {
            it.isSelected = !it.isSelected // 수정인 상태에서 클릭을하면 true로 바뀜
            homeViewModel.setIsEditClick(it.isSelected) // 수정을 눌렀다! true
        }
    }

    private fun setStickerClickListener() {
        // 홈(메인) 약 리스트 스티커 클릭-바텀시트 띄우기(고차함수 써보기-바텀네비 가리면서 올라와야 하니까 MainActivity 에서 띄워주려고)
        homePillListAllAdapter.setStickerClickListener {
            mainViewModel.setIsStickerClick(it)
        }
    }

    // 홈(메인) 수정<->완료, 수정 터치 시 체크 버튼<->컨텍스트 버튼
    private fun observeIsEditClickEvent() {
        homeViewModel.isEditClick.observe(this) {
            binding.isEditText = !it
            homePillListAllAdapter.setIsEdit(it)
        }
    }

    private fun getHomePillList() {
        homeViewModel.getHomePillListData()
    }

    private fun observeHomePillList() {
        homeViewModel.homePillList.observe(viewLifecycleOwner) {
            homePillListAllAdapter.homePillListAll = it.data
            binding.isEmpty = it.data.isEmpty()
        }
    }

    private fun getSharePillList() {
        homeViewModel.getSharePillListData()
    }

    private fun observeSharePillList() {
        homeViewModel.sharePillList.observe(viewLifecycleOwner) {
            homePillListAllAdapter.homePillListAll = it.data
            binding.isEmpty = it.data.isEmpty()
        }
    }


}