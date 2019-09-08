package ru.ktsstudio.wishlist.di.providers.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@InjectConstructor
class OkHttpProvider(
    @Named("logging") private val loggingInterceptor: Interceptor,
    @Named("token") private val tokenInterceptor: Interceptor,
    @Named("http_status") private val httpStatusInterceptor: Interceptor
) : Provider<OkHttpClient> {
    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(httpStatusInterceptor)
            .build()
    }
}