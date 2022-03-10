package com.bryo.marvel.myheroteam.core.providers.host

import com.bryo.marvel.myheroteam.core.providers.Provider

class BaseUrlProvider: Provider<String> {
    override fun get() = "https://gateway.marvel.com"
}