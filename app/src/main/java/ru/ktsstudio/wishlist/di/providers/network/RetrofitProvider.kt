package ru.ktsstudio.wishlist.di.providers.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Provider

@InjectConstructor
class RetrofitProvider(
    private val okhttp: OkHttpClient,
    private val gson: Gson
) : Provider<Retrofit> {
    override fun get(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://summer19-android.k8s.ktsstudio.com")
            .client(okhttp)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}