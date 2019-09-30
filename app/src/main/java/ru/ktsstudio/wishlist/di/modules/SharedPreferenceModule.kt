package ru.ktsstudio.wishlist.di.modules

import android.content.SharedPreferences
import ru.ktsstudio.wishlist.data.prefs.ISharedPreferencesRepository
import ru.ktsstudio.wishlist.data.prefs.SharedPreferencesRepository
import ru.ktsstudio.wishlist.di.providers.prefs.SharedPreferencesProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class SharedPreferenceModule : Module() {

    init {
        bind<SharedPreferences>().toProvider(SharedPreferencesProvider::class.java).providesSingleton()
        bind<ISharedPreferencesRepository>().toClass<SharedPreferencesRepository>().singleton()
    }

}