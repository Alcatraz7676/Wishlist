package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.data.db.repository.IWishRepository
import ru.ktsstudio.wishlist.di.providers.db.DatabaseProvider
import ru.ktsstudio.wishlist.di.providers.db.WishDaoProvider
import ru.ktsstudio.wishlist.data.db.repository.WishRepository
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class DatabaseModule : Module() {

    init {
        bind<WishDatabase>().toProvider(DatabaseProvider::class.java).providesSingleton()
        bind<WishDao>().toProvider(WishDaoProvider::class.java).providesSingleton()
        bind<IWishRepository>().toClass<WishRepository>().singleton()
    }

}