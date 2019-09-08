package ru.ktsstudio.wishlist.di.providers.db

import android.content.Context
import ru.ktsstudio.wishlist.data.db.WishDatabase
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Provider

@InjectConstructor
class DatabaseProvider(
    private val applicationContext: Context
) : Provider<WishDatabase> {
    override fun get(): WishDatabase {
        return WishDatabase.getInstance(applicationContext)
    }
}