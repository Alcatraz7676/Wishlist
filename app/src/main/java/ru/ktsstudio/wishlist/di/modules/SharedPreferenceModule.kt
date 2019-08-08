package ru.ktsstudio.wishlist.di.modules

import android.content.SharedPreferences
import ru.ktsstudio.wishlist.di.providers.SharedPreferencesProvider
import toothpick.config.Module

class SharedPreferenceModule : Module() {

    init {
        bind(SharedPreferences::class.java).toProvider(SharedPreferencesProvider::class.java).providesSingletonInScope()
    }

}