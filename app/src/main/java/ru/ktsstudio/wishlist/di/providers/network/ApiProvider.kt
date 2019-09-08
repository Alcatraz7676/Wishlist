package ru.ktsstudio.wishlist.di.providers.network

import retrofit2.Retrofit
import ru.ktsstudio.wishlist.data.network.WishApiService
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class ApiProvider(
    private val retrofit: Retrofit
) : Provider<WishApiService> {
    override fun get(): WishApiService {
        return retrofit.create(WishApiService::class.java)
    }
}