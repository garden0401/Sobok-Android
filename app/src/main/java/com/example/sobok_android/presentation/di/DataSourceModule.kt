package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddRemoteDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<PillAddDataSource> { PillAddRemoteDataSource(get()) }
    single<ShareRequestDataSource> { ShareRequestRemoteDataSource(get()) }
}