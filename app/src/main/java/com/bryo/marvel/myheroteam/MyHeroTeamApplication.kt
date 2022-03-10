package com.bryo.marvel.myheroteam

import android.app.Application
import com.bryo.marvel.myheroteam.core.di.*
import com.bryo.marvel.myheroteam.di.useCaseModule
import com.bryo.marvel.myheroteam.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyHeroTeamApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidLogger()
            androidContext(this@MyHeroTeamApplication)
            modules(
                listOfNotNull(
                    viewModelModule,
                    repositoryModule,
                    mapperModule,
                    useCaseModule,
                    networkModule,
                    providerModule,
                    databaseModule
                )
            )
        }
    }
}