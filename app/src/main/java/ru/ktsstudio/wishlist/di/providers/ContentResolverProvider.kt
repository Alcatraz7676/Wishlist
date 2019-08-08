package ru.ktsstudio.wishlist.di.providers

import android.app.Application
import android.content.ContentResolver
import javax.inject.Inject
import javax.inject.Provider

class ContentResolverProvider @Inject constructor(
    private val application: Application
) : Provider<ContentResolver> {
    override fun get(): ContentResolver {
        return application.applicationContext.contentResolver
    }
}