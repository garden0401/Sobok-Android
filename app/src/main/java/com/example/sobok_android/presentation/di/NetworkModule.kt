package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.api.LoginService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjQsImVtYWlsIjoiYmJkQGdtYWlsLmNvbSIsIm5hbWUiOm51bGwsImlkRmlyZWJhc2UiOiJaNVRBM2VWaFVwZHdCV2hHdEpiOVJsWkZMM3YyIiwiaWF0IjoxNjQxODkzNjc1LCJleHAiOjE2NDQ0ODU2NzUsImlzcyI6Indlc29wdCJ9.Qc20TVDA2ptT8SClPC6TsCeFK8_3wQX0RISunGCX90k"
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
    single<LoginService> {
        get<Retrofit>().create(LoginService::class.java)
    }


}