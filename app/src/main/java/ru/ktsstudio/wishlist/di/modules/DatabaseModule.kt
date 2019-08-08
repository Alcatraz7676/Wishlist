package ru.ktsstudio.wishlist.di.modules

import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.di.providers.DatabaseProvider
import ru.ktsstudio.wishlist.di.providers.WishDaoProvider
import toothpick.config.Module

class DatabaseModule : Module() {

    init {
        bind(WishDatabase::class.java).toProvider(DatabaseProvider::class.java).providesSingletonInScope()
        bind(WishDao::class.java).toProvider(WishDaoProvider::class.java).providesSingletonInScope()
    }
}