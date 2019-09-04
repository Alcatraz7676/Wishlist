package ru.ktsstudio.wishlist.ui

import android.app.Application
import com.facebook.stetho.Stetho
import ru.ktsstudio.wishlist.BuildConfig
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.di.modules.*
import toothpick.Toothpick

class WishApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDi() {
        val scope = Toothpick.openScope(DI.APP)
        scope.installModules(
            AppModule(this),
            SharedPreferenceModule(),
            DatabaseModule(),
            NetworkModule(),
            NavigationModule()
        )
        Toothpick.inject(this, scope)
    }

}