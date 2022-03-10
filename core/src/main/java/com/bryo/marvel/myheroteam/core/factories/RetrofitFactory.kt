package com.bryo.marvel.myheroteam.core.factories

import com.bryo.marvel.myheroteam.core.factories.OkHttpFactory.okHttpClient
import com.bryo.marvel.myheroteam.core.providers.host.BaseUrlProvider
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory : KoinComponent {

    private val baseUrlProvider = get<BaseUrlProvider>()

    fun retrofitWith(okHttpClient: OkHttpClient = okHttpClient()): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrlProvider.get())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}