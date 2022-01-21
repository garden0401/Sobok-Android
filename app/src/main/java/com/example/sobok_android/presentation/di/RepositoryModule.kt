package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.repository.notice.NoticeRepositoryImpl
import com.example.sobok_android.domain.repository.notice.NoticeRepository
import com.example.sobok_android.data.repository.pill.pilladd.PillAddRepositoryImpl
import com.example.sobok_android.data.repository.share.request.ShareRequestRepositoryImpl
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<NoticeRepository> {
        NoticeRepositoryImpl(get())
    }
    single<PillAddRepository> { PillAddRepositoryImpl(get())}
    single<ShareRequestRepository> {ShareRequestRepositoryImpl(get())}
}