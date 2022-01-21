package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.api.PillAddService
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
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjcsImVtYWlsIjoic29ib2tAZ21haWwuY29tIiwibmFtZSI6bnVsbCwiaWRGaXJlYmFzZSI6Im52NzdlS0Z3T1FURU0zTVRvcUlNbW9QQlR6bDEiLCJpYXQiOjE2NDIwOTMxMDcsImV4cCI6MTY0NDY4NTEwNywiaXNzIjoid2Vzb3B0In0.eXLGkqQlrEqlWZCvMdJCtaTRNUCOwg7vT6clQkD6NZ4"
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
    single<PillAddService> {
        get<Retrofit>().create(PillAddService::class.java)
    }

}