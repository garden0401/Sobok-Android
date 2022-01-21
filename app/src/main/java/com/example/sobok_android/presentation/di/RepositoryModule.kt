package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.repository.calendar.CalendarRepositoryImpl
import com.example.sobok_android.data.repository.pill.pilladd.PillAddRepositoryImpl
import com.example.sobok_android.data.repository.share.request.ShareRequestRepositoryImpl
import com.example.sobok_android.domain.repository.calendar.CalendarRepository
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PillAddRepository> { PillAddRepositoryImpl(get())}
    single<ShareRequestRepository> {ShareRequestRepositoryImpl(get())}
    single<CalendarRepository> {CalendarRepositoryImpl(get())}
}