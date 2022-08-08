package com.example.sobok_android.presentation.di

import android.app.Application
import com.example.sobok_android.BuildConfig
import com.example.sobok_android.data.sharedpref.SobokSharedPreference
import com.example.sobok_android.domain.model.share.request.SearchResultData
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SobokApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SobokApplication)
            modules(netWorkModule, dataSourceModule, repositoryModule, viewModelModule)
        }

        SobokSharedPreference.init(applicationContext)

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}