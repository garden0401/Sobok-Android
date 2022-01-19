package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.notice.NoticeDataSource
import com.example.sobok_android.data.datasource.notice.NoticeLocalDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddLocalDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single<NoticeDataSource> {
        NoticeLocalDataSource()
    }
    single<PillAddDataSource> { PillAddLocalDataSource() }
    single<ShareRequestDataSource> { ShareRequestRemoteDataSource(get()) }
}