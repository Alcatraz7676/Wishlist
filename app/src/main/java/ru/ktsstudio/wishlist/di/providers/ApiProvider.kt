package ru.ktsstudio.wishlist.di.providers

import retrofit2.Retrofit
import ru.ktsstudio.wishlist.data.network.WishApiService
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<WishApiService> {
    override fun get(): WishApiService {
        return retrofit.create(WishApiService::class.java)
    }
}