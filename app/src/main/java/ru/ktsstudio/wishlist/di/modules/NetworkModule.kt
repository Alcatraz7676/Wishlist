package ru.ktsstudio.wishlist.di.modules

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.ktsstudio.wishlist.data.network.interceptors.AddTokenInterceptor
import ru.ktsstudio.wishlist.data.network.interceptors.HttpStatusInterceptor
import ru.ktsstudio.wishlist.data.network.WishApiService
import ru.ktsstudio.wishlist.data.network.repository.IWishApiRepository
import ru.ktsstudio.wishlist.data.network.repository.WishApiRepository
import ru.ktsstudio.wishlist.di.providers.network.*
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class NetworkModule : Module() {

    init {
        bind<Gson>().toProvider(GsonProvider::class.java).providesSingleton()
        bind<Interceptor>().withName("logging").toProvider(LoggingInterceptorProvider::class.java).providesSingleton()
        bind<Interceptor>().withName("token").toClass<AddTokenInterceptor>().singleton()
        bind<Interceptor>().withName("http_status").toClass<HttpStatusInterceptor>().singleton()
        bind<OkHttpClient>().toProvider(OkHttpProvider::class.java).providesSingleton()
        bind<Retrofit>().toProvider(RetrofitProvider::class.java).providesSingleton()
        bind<WishApiService>().toProvider(ApiProvider::class.java).providesSingleton()
        bind<IWishApiRepository>().toClass<WishApiRepository>().singleton()
    }

}