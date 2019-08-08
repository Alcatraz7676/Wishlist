package ru.ktsstudio.wishlist.di.providers

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import ru.ktsstudio.wishlist.BuildConfig
import javax.inject.Inject
import javax.inject.Provider

private const val PREFS_NAME = BuildConfig.APPLICATION_ID + "MY_PREFS"

class SharedPreferencesProvider @Inject constructor(
    private val application: Application
) : Provider<SharedPreferences> {
    override fun get(): SharedPreferences {
        return application.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}