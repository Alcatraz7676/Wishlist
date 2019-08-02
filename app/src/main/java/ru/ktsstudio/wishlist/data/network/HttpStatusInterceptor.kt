package ru.ktsstudio.wishlist.data.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.ktsstudio.wishlist.data.models.response.AuthResponse
import ru.ktsstudio.wishlist.data.stores.GsonStore
import javax.net.ssl.HttpsURLConnection

class HttpStatusInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == HttpsURLConnection.HTTP_UNAUTHORIZED) throw UnauthorizedException()
        if (response.code() == HttpsURLConnection.HTTP_BAD_REQUEST) {
            val authJson = GsonStore.instance.fromJson(response.body()?.string(), AuthResponse::class.java)
            if (authJson.message == "user exists") throw UserExistsException()
        }

        return response
    }

    class UnauthorizedException : Exception()
    class UserExistsException : Exception()

}