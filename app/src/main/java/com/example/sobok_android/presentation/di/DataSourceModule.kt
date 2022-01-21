package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.datasource.notice.NoticeDataSource
import com.example.sobok_android.data.datasource.notice.NoticeRemoteDataSource
import com.example.sobok_android.data.datasource.calendar.CalendarDataSource
import com.example.sobok_android.data.datasource.calendar.CalendarRemoteDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddDataSource
import com.example.sobok_android.data.datasource.pill.pilladd.PillAddRemoteDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestDataSource
import com.example.sobok_android.data.datasource.share.request.ShareRequestRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
<<<<<<< HEAD

    single<NoticeDataSource> {
        NoticeRemoteDataSource(get())
    }
    single<PillAddDataSource> { PillAddLocalDataSource() }
=======
    single<PillAddDataSource> { PillAddRemoteDataSource(get()) }
>>>>>>> 24-pill-add-one-page
    single<ShareRequestDataSource> { ShareRequestRemoteDataSource(get()) }
    single<CalendarDataSource> { CalendarRemoteDataSource(get()) }
}