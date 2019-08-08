package ru.ktsstudio.wishlist.di.providers

import android.util.Log
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Provider

class LoggingInterceptorProvider @Inject constructor() : Provider<Interceptor> {
    override fun get(): Interceptor {
        return HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) }
        )
            .apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
    }
}