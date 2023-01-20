package com.adam.catzapp

import android.app.Application
import com.adam.catzapp.di.useCaseModule
import com.adam.catzapp.di.viewModelModule
import com.adam.core.di.databaseModule
import com.adam.core.di.networkModule
import com.adam.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

open class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            ))
        }
    }
}