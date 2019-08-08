package ru.ktsstudio.wishlist.di.providers

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

class OkHttpProvider @Inject constructor(
    @Named("logging") private val loggingInterceptor: Interceptor,
    @Named("token") private val tokenInterceptor: Interceptor,
    @Named("http_status") private val httpInterceptor: Interceptor
) : Provider<OkHttpClient> {
    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(httpInterceptor)
            .build()
    }
}