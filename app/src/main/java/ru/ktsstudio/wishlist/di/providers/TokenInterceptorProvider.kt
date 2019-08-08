package ru.ktsstudio.wishlist.di.providers

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.data.network.AddTokenHeaderInterceptor
import ru.ktsstudio.wishlist.ui.app.WishApp
import ru.ktsstudio.wishlist.utils.KEY_TOKEN
import javax.inject.Inject
import javax.inject.Provider

class TokenInterceptorProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Provider<Interceptor> {
    override fun get(): Interceptor {
        return AddTokenHeaderInterceptor(sharedPreferences)
    }
}