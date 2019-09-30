package ru.ktsstudio.wishlist.data.prefs

import android.content.SharedPreferences
import toothpick.InjectConstructor

const val KEY_TOKEN = "TOKEN"
const val KEY_USER_LOGIN = "USER_LOGIN"

@InjectConstructor
class SharedPreferencesRepository(
    private val sharedPreferences: SharedPreferences
) : ISharedPreferencesRepository {

    override fun getCurrentUserLogin(): String? {
        return sharedPreferences.getString(KEY_USER_LOGIN, null)
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun saveUser(token: String?, email: String?) {
        val editor = sharedPreferences.edit()
        editor.apply {
            putString(KEY_TOKEN, token)
            putString(KEY_USER_LOGIN, email)
        }
        editor.apply()
    }

    override fun clearUser() {
        sharedPreferences.edit().apply {
            remove(KEY_USER_LOGIN)
            remove(KEY_TOKEN)
        }.apply()
    }

}