package ru.ktsstudio.wishlist.data.network

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.utils.KEY_TOKEN

class AddTokenHeaderInterceptor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(KEY_TOKEN, null)
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
