package com.bryo.marvel.myheroteam.core.di

import com.bryo.marvel.myheroteam.core.factories.RetrofitFactory.retrofitWith
import com.bryo.marvel.myheroteam.core.network.MarvelApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module {

    factory {
        OkHttpClient.Builder()
    }

    factory { retrofitWith().create(MarvelApiService::class.java) }
}