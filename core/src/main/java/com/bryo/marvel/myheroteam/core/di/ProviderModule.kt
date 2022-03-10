package com.bryo.marvel.myheroteam.core.di

import com.bryo.marvel.myheroteam.core.providers.host.BaseUrlProvider
import org.koin.dsl.module

val providerModule = module {
    factory { BaseUrlProvider() }
}
