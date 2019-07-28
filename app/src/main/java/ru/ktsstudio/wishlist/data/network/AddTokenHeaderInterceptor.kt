package ru.ktsstudio.wishlist.data.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.data.stores.TokenStore

class AddTokenHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = TokenStore.token
        val originalRequest = chain.request()

        val request = if (token != null) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}
