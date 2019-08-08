package ru.ktsstudio.wishlist.di.providers

import android.app.Application
import androidx.room.RoomDatabase
import ru.ktsstudio.wishlist.data.db.WishDatabase
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(
    private val application: Application
) : Provider<WishDatabase> {
    override fun get(): WishDatabase {
        return WishDatabase.getInstance(application.applicationContext)
    }
}