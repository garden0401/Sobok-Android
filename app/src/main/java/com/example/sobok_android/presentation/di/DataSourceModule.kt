package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.login.SignInDataSource
import com.example.sobok_android.data.datasource.login.SignInRemoteDataSource
import com.example.sobok_android.data.datasource.login.SignUpDataSource
import com.example.sobok_android.data.datasource.login.SignUpRemoteDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddLocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<PillAddDataSource> { PillAddLocalDataSource() }

    single<SignInDataSource> { SignInRemoteDataSource(get()) }
    single<SignUpDataSource> { SignUpRemoteDataSource(get()) }
}