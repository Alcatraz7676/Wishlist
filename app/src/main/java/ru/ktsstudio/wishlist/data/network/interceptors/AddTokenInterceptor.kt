package ru.ktsstudio.wishlist.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.data.prefs.SharedPreferenceRepository
import toothpick.InjectConstructor

@InjectConstructor
class AddTokenInterceptor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferenceRepository.getToken()
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
