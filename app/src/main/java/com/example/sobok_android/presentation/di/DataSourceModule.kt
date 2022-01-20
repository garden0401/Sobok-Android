package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.calendar.CalendarDataSource
import com.example.sobok_android.data.datasource.calendar.CalendarRemoteDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddLocalDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<PillAddDataSource> { PillAddLocalDataSource() }
    single<ShareRequestDataSource> { ShareRequestRemoteDataSource(get()) }
    single<CalendarDataSource> { CalendarRemoteDataSource(get()) }
}