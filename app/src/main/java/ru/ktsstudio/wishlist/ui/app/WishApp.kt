package ru.ktsstudio.wishlist.ui.app

import android.app.Application
import androidx.room.RoomDatabase
import com.facebook.stetho.Stetho
import ru.ktsstudio.wishlist.BuildConfig
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.di.DI
import ru.ktsstudio.wishlist.di.modules.*
import toothpick.Toothpick
import javax.inject.Inject

class WishApp : Application() {

    @Inject
    lateinit var wishDatabase: WishDatabase

    override fun onCreate() {
        super.onCreate()
        initDi()
        wishDatabase.openHelper.writableDatabase
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDi() {
        val scope = Toothpick.openScope(DI.APP)
        scope.installModules(
            AppModule(this),
            NetworkModule(),
            DatabaseModule(),
            SharedPreferenceModule(),
            ContentProviderModule()
        )
        Toothpick.inject(this, scope)
    }

}