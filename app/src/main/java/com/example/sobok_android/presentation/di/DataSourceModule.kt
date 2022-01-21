package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.notice.NoticeDataSource
import com.example.sobok_android.data.datasource.notice.NoticeRemoteDataSource
import com.example.sobok_android.data.datasource.calendar.CalendarDataSource
import com.example.sobok_android.data.datasource.calendar.CalendarRemoteDataSource
import com.example.sobok_android.data.datasource.login.SignInDataSource
import com.example.sobok_android.data.datasource.login.SignInRemoteDataSource
import com.example.sobok_android.data.datasource.login.SignUpDataSource
import com.example.sobok_android.data.datasource.login.SignUpRemoteDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddRemoteDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<NoticeDataSource> { NoticeRemoteDataSource(get()) }
    single<PillAddDataSource> { PillAddRemoteDataSource(get()) }
    single<ShareRequestDataSource> { ShareRequestRemoteDataSource(get()) }
    single<CalendarDataSource> { CalendarRemoteDataSource(get()) }
    single<SignInDataSource> { SignInRemoteDataSource(get()) }
    single<SignUpDataSource> { SignUpRemoteDataSource(get()) }
}