package ru.ktsstudio.wishlist

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.Stetho
import ru.ktsstudio.wishlist.data.db.WishDatabase

class WishApp : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        WishDatabase.getInstance().openHelper.writableDatabase
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {

        private const val PREFS_NAME = BuildConfig.APPLICATION_ID + "MY_PREFS"

        private lateinit var instance: WishApp
        val sharedPreferences: SharedPreferences by lazy {
            applicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

}