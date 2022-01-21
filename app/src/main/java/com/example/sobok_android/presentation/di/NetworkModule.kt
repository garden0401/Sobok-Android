package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.api.NoticeService
import com.example.sobok_android.data.api.CalendarService
import com.example.sobok_android.data.api.ShareService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjIsImVtYWlsIjoiZWRAZ21haWwuY29tIiwibmFtZSI6bnVsbCwiaWRGaXJlYmFzZSI6InVOR2llMWxKWDNTREpTQnFSWHhLZUhqMnJhMzMiLCJpYXQiOjE2NDE4ODYzNjUsImV4cCI6MTY0NDQ3ODM2NSwiaXNzIjoid2Vzb3B0In0.K9xOhsd1G3sHAo89LRbLHaPySX8PeOW3kxvbbYaVeNA"
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

}