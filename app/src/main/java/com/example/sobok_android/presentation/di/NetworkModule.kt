package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.api.*
import com.example.sobok_android.data.sharedpref.SobokSharedPreference
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val netWorkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(
                            "accesstoken",
                            SobokSharedPreference.getUserToken()
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

    single<MyInfoService>{
        get<Retrofit>().create(MyInfoService::class.java)
    }
}