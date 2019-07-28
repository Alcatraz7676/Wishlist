package ru.ktsstudio.wishlist.data.stores

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.ktsstudio.wishlist.data.network.WishApiService

object RetrofitStore {

    private val instance: Retrofit = Retrofit.Builder()
        .baseUrl("https://summer19-android.k8s.ktsstudio.com")
        .client(OkHttp.instance)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonStore.instance))
        .build()

    val service: WishApiService = instance.create(WishApiService::class.java)
}