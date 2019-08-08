package ru.ktsstudio.wishlist.di.providers

import ru.ktsstudio.wishlist.data.db.WishDao
import ru.ktsstudio.wishlist.data.db.WishDatabase
import javax.inject.Inject
import javax.inject.Provider

class WishDaoProvider @Inject constructor(
    private val database: WishDatabase
) : Provider<WishDao> {
    override fun get(): WishDao {
        return database.wishDao()
    }
}