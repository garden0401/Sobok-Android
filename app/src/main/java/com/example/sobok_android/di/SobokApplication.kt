package com.example.sobok_android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SobokApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@SobokApplication)
            modules(netWorkModule, dataSourceModule, repositoryModule, viewModelModule)
        }
    }
}