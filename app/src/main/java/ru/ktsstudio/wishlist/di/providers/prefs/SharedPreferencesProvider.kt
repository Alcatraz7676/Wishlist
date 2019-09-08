package ru.ktsstudio.wishlist.di.providers.prefs

import android.content.Context
import android.content.SharedPreferences
import ru.ktsstudio.wishlist.BuildConfig
import toothpick.InjectConstructor
import javax.inject.Provider

private const val PREFS_NAME = BuildConfig.APPLICATION_ID + "MY_PREFS"

@InjectConstructor
class SharedPreferencesProvider(
    private val applicationContext: Context
) : Provider<SharedPreferences> {
    override fun get(): SharedPreferences {
        return applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}