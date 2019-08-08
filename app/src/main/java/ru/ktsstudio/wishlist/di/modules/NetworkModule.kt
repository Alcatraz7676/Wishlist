package ru.ktsstudio.wishlist.di.modules

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.di.providers.*
import toothpick.config.Module

class NetworkModule : Module() {

    init {
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingletonInScope()
        bind(Interceptor::class.java).withName("logging").toProvider(LoggingInterceptorProvider::class.java)
        bind(Interceptor::class.java).withName("token").toProvider(TokenInterceptorProvider::class.java)
        bind(Interceptor::class.java).withName("http_status").toProvider(HttpStatusInterceptorProvider::class.java)
        bind(OkHttpClient::class.java).toProvider(OkHttpProvider::class.java).providesSingletonInScope()
        bind(Retrofit::class.java).toProvider(RetrofitProvider::class.java).providesSingletonInScope()
        bind(WishApiService::class.java).toProvider(ApiProvider::class.java).instancesInScope()
    }

}