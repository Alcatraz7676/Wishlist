package ru.ktsstudio.wishlist.di.providers.db

import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.db.WishDatabase
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Provider

@InjectConstructor
class WishDaoProvider(
    private val database: WishDatabase
) : Provider<WishDao> {
    override fun get(): WishDao {
        return database.wishDao()
    }
}