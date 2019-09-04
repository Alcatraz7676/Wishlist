package ru.ktsstudio.wishlist.di.modules

import android.content.ContentResolver
import ru.ktsstudio.wishlist.di.providers.content_provider.ContentResolverProvider
import ru.ktsstudio.wishlist.data.content_provider.ContentProviderRepository
import ru.ktsstudio.wishlist.data.content_provider.IContentProviderRepository
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class ContentProviderModule : Module() {

    init {
        bind<ContentResolver>().toProvider(ContentResolverProvider::class.java).providesSingleton()
        bind<IContentProviderRepository>().toClass<ContentProviderRepository>().singleton()
    }

}