package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.repository.NoticeRepositoryImpl
import com.example.sobok_android.domain.repository.NoticeRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<NoticeRepository> {
        NoticeRepositoryImpl(get())
    }
}