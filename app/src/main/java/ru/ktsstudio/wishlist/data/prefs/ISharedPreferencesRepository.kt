package ru.ktsstudio.wishlist.data.prefs

interface ISharedPreferencesRepository {

    fun getCurrentUserLogin(): String?

    fun getToken(): String?

    fun saveUser(token: String?, email: String?)

    fun clearUser()

}