package ru.ktsstudio.wishlist.data.stores

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonStore {

    val instance: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}