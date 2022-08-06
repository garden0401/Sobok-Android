package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.repository.notice.NoticeRepositoryImpl
import com.example.sobok_android.domain.repository.notice.NoticeRepository
import com.example.sobok_android.data.repository.calendar.CalendarRepositoryImpl
import com.example.sobok_android.data.repository.login.request.SignInRepositoryImpl
import com.example.sobok_android.data.repository.login.request.SignUpRepositoryImpl
import com.example.sobok_android.data.repository.notice2.NoticeRepositoryImpl2
import com.example.sobok_android.data.repository.pill.pilladd.PillAddRepositoryImpl
import com.example.sobok_android.data.repository.share.request.ShareRequestRepositoryImpl
import com.example.sobok_android.domain.repository.calendar.CalendarRepository
import com.example.sobok_android.domain.repository.login.request.SignInRepository
import com.example.sobok_android.domain.repository.login.request.SignUpRepository
import com.example.sobok_android.domain.repository.notice2.NoticeRepository2
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import com.example.sobok_android.domain.repository.share.request.ShareRequestRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<NoticeRepository> {
        NoticeRepositoryImpl(get())
    }
    single<PillAddRepository> { PillAddRepositoryImpl(get())}
    single<ShareRequestRepository> {ShareRequestRepositoryImpl(get())}
    single<CalendarRepository> {CalendarRepositoryImpl(get())}

    single<SignInRepository> {SignInRepositoryImpl(get())}
    single<SignUpRepository> { SignUpRepositoryImpl(get()) }
    single<NoticeRepository2> { NoticeRepositoryImpl2(get()) }
}