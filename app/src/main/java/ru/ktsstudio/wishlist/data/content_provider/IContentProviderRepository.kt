package ru.ktsstudio.wishlist.data.content_provider

import io.reactivex.Maybe

interface IContentProviderRepository {

    fun getContactNames(): Maybe<List<String>>

}