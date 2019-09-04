package ru.ktsstudio.wishlist.data.network.interceptors

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.data.network.model.response.AuthResponse
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class HttpStatusInterceptor @Inject constructor(
    private val gsonInstance: Gson
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == HttpsURLConnection.HTTP_UNAUTHORIZED) throw UnauthorizedException()
        if (response.code() == HttpsURLConnection.HTTP_BAD_REQUEST) {
            val authJson = gsonInstance.fromJson(response.body()?.string(), AuthResponse::class.java)
            if (authJson.message == "user exists") throw UserExistsException()
        }

        return response
    }

    class UnauthorizedException : Exception()
    class UserExistsException : Exception()

}