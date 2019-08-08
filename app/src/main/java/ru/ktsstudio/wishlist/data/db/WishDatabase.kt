package ru.ktsstudio.wishlist.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.ui.app.WishApp
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.stores.LocalWishesStore

@Database(entities = [Wish::class], version = 1)
abstract class WishDatabase : RoomDatabase() {

    abstract fun wishDao(): WishDao

    companion object {

        private var INSTANCE: WishDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): WishDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context)
                }
                return INSTANCE!!
            }
        }

        private fun buildDatabase(context: Context): WishDatabase {
            return Room.databaseBuilder(
                context,
                WishDatabase::class.java,
                "Wish.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        getInstance(context)
                            .wishDao()
                            .insertAll(LocalWishesStore.getAllWishes())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe{ items ->
                                Log.d("WishDatabase", "Insert count = ${items.count()}" )
                            }
                    }
                })
                .build()
        }
    }
}