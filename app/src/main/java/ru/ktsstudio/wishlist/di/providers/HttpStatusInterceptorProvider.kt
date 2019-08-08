package ru.ktsstudio.wishlist.di.providers

import com.google.gson.Gson
import okhttp3.Interceptor
import ru.ktsstudio.wishlist.data.network.HttpStatusInterceptor
import javax.inject.Inject
import javax.inject.Provider

class HttpStatusInterceptorProvider @Inject constructor(
    private val gson: Gson
) : Provider<Interceptor> {
    override fun get(): Interceptor {
        return HttpStatusInterceptor(gson)
    }
}
