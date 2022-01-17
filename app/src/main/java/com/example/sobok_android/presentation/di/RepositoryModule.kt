package com.example.sobok_android.presentation.di

import com.example.sobok_android.data.repository.pill.pilladd.PillAddRepositoryImpl
import com.example.sobok_android.domain.repository.pill.pilladd.PillAddRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PillAddRepository> { PillAddRepositoryImpl(get())}

}