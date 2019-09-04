package ru.ktsstudio.wishlist.di.providers.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class LoggingInterceptorProvider : Provider<Interceptor> {
    override fun get(): Interceptor {
        return HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", message) }
        )
            .apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
    }
}