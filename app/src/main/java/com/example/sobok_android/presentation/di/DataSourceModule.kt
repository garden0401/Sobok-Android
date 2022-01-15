package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.notice.NoticeDataSource
import com.example.sobok_android.data.datasource.notice.NoticeLocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<NoticeDataSource> {
        NoticeLocalDataSource()
    }
}