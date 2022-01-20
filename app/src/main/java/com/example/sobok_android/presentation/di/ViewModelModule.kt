package com.example.sobok_android.presentation.di

import com.example.sobok_android.presentation.view.pill.add.viewmodel.PillAddViewModel
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignInViewModel
import com.example.sobok_android.presentation.view.user.request.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PillAddViewModel(get())}

    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}