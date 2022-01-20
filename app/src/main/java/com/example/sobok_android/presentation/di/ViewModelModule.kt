package com.example.sobok_android.presentation.di

import com.example.sobok_android.presentation.view.calendar.viewmodel.CalendarViewModel
import com.example.sobok_android.presentation.view.home.viewmodel.HomeViewModel
import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.presentation.view.share.request.viewmodel.ShareRequestViewModel
import com.example.sobok_android.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { PillAddViewModel(get())}
    viewModel { ShareRequestViewModel(get()) }
    viewModel { CalendarViewModel(get()) }
}