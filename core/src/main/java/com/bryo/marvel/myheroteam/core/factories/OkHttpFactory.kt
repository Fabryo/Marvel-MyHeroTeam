package com.bryo.marvel.myheroteam.core.factories

import com.bryo.marvel.myheroteam.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import org.koin.core.KoinComponent
import org.koin.core.get
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit.MINUTES

object OkHttpFactory : KoinComponent {
    private const val PARAM_HASH = "hash"
    private const val PARAM_API_KEY = "apikey"
    private const val PARAM_TS = "ts"
    private const val TS_DEFAULT_VALUE = "1"

    fun okHttpClient(): OkHttpClient {
        return clientBuilder()
            .build()
    }

    private fun clientBuilder(): Builder {
        return get<Builder>()
            .connectTimeout(2, MINUTES)
            .readTimeout(2, MINUTES)
            .writeTimeout(2, MINUTES)
            .addMarvelApiInterceptor()
    }

    private fun Builder.addMarvelApiInterceptor(): Builder =
        addInterceptor { chain ->
            val hash = md5("$TS_DEFAULT_VALUE${BuildConfig.MarvelApiPrivateKey}${BuildConfig.MarvelApiPublicKey}")
            val httpUrl = chain.request().url.newBuilder()
                .addQueryParameter(PARAM_TS, TS_DEFAULT_VALUE)
                .addQueryParameter(PARAM_API_KEY, BuildConfig.MarvelApiPublicKey)
                .addQueryParameter(PARAM_HASH, hash)
                .build()

            val requestBuilder = chain.request().newBuilder().url(httpUrl)

            chain.proceed(requestBuilder.build())
        }

    private fun md5(toHash: String): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(toHash.toByteArray()))
            .toString(16)
            .padStart(32, '0')

}