package com.example.sobok_android.presentation.di

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
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjYsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJuYW1lIjpudWxsLCJpZEZpcmViYXNlIjoiTnBRVmhYdUg3eVUwUkpVdUV6Zks3NldWckFGMiIsImlhdCI6MTY0MjA5MjkwMiwiZXhwIjoxNjQ0Njg0OTAyLCJpc3MiOiJ3ZXNvcHQifQ.fZ4bodbWJ3AlgD_c0oE5OyAW2WaXDeQHtApZLaZjdGI"
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

}