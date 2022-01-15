package com.example.sobok_android.presentation.di

import com.example.sobok_android.presentation.view.notice.viewmodel.NoticeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NoticeViewModel(get())
    }
}