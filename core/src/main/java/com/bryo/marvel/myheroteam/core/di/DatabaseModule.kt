package com.bryo.marvel.myheroteam.core.di

import android.app.Application
import androidx.room.Room
import com.bryo.marvel.myheroteam.core.database.MarvelHeroTeamDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDataBase(application: Application): MarvelHeroTeamDatabase {
        return Room.databaseBuilder(application, MarvelHeroTeamDatabase::class.java, "MarvelHeroTeamDB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { provideDataBase(androidApplication()) }
    single { get<MarvelHeroTeamDatabase>().charactersDao() }
    single { get<MarvelHeroTeamDatabase>().fetchServerDao() }
}