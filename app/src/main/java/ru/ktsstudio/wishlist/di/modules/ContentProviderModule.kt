package ru.ktsstudio.wishlist.di.modules

import android.content.ContentResolver
import ru.ktsstudio.wishlist.di.providers.ContentResolverProvider
import toothpick.config.Module

class ContentProviderModule : Module() {

    init {
        bind(ContentResolver::class.java).toProvider(ContentResolverProvider::class.java)
    }

}