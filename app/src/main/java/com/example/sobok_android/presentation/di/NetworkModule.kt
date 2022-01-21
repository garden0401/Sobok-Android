package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.api.NoticeService
import com.example.sobok_android.data.api.CalendarService
import com.example.sobok_android.data.api.PillAddService
import com.example.sobok_android.data.api.ShareService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.sobok_android.data.api.LoginService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(
                            "accesstoken",
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NzQsImVtYWlsIjoiYW9zMUBnbWFpbC5jb20iLCJuYW1lIjpudWxsLCJpZEZpcmViYXNlIjoiWGRsU0VuQ3VlYU52WWhXcVZRZlUySHBDSkQxMyIsImlhdCI6MTY0Mjc2Nzk5NiwiZXhwIjoxNjQ1MzU5OTk2LCJpc3MiOiJzb2JvayJ9.NLvI0MiyKX1lhEV8xGMLSoD24PwjMUyFKdRa80-6Xak"
                        )
                        .build()
                )
            })
            .build()
    }

    single<Retrofit>{
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())) //GsonBuilder().setLenient().create()
            .baseUrl("https://asia-northeast3-sobok-76d0a.cloudfunctions.net/api/")
            .build()
    }
    single<ShareService> {
        get<Retrofit>().create(ShareService::class.java)
    }
    single<NoticeService> {
        get<Retrofit>().create(NoticeService::class.java)
    }
    single<CalendarService> {
        get<Retrofit>().create(CalendarService::class.java)
    }
    single<PillAddService> {
        get<Retrofit>().create(PillAddService::class.java)
    }

    single<LoginService> {
        get<Retrofit>().create(LoginService::class.java)
    }
}