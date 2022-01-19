package com.example.sobok_android.presentation.view.pill.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sobok_android.R
import com.example.sobok_android.databinding.ActivityPillAddBinding
import com.example.sobok_android.presentation.base.BindingActivity
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PillAddActivity : BindingActivity<ActivityPillAddBinding>(R.layout.activity_pill_add) {

    private val pillAddViewModel : PillAddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 여기서 viewModel 함수 불러온다

        binding.clPillPerson.setOnClickListener {
            // 약추가할사람 목록 피커 dialog
            // 데이터 필요
        }
        binding.clAddPillName.setOnClickListener {
            // 함께 먹는 약 추가..
        }
        binding.clPillDate.setOnClickListener {
            // 기간 선택
        }
        binding.tvPillDateEveryday.setOnClickListener {
            // 매일 선택
        }
        binding.tvPillDateSpecificDay.setOnClickListener {
            // 특정요일 선택
            // 특정 요일 피커 등장
        }
        binding.tvPillDateSpecificPeriod.setOnClickListener {
            // 특정 기간 선택
            // 특정 기간 피커 등장
        }

        // 약 복약시간 리사이클러뷰..의 삭제 추가

        binding.clAddPillTime.setOnClickListener {
            // 시간 추가
        }

    }
}