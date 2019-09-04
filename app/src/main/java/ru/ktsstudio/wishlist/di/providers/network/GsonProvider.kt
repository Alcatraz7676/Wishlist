package ru.ktsstudio.wishlist.di.providers.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import toothpick.InjectConstructor
import javax.inject.Inject
import javax.inject.Provider

@InjectConstructor
class GsonProvider : Provider<Gson> {
    override fun get(): Gson {
        return GsonBuilder()
            .enableComplexMapKeySerialization()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}