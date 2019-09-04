package ru.ktsstudio.wishlist.di.providers.content_provider

import android.content.ContentResolver
import android.content.Context
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Provider

@InjectConstructor
class ContentResolverProvider(
    private val applicationContext: Context
) : Provider<ContentResolver> {
    override fun get(): ContentResolver {
        return applicationContext.contentResolver
    }
}