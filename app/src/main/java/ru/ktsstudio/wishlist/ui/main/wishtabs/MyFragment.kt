package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.WishApp
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.data.models.User
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.data.stores.LocalWishesStore
import ru.ktsstudio.wishlist.data.stores.RetrofitStore
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN

class MyFragment : WishFragment() {

    override fun getWishes(): Observable<List<Wish>> {
        val currentUser = WishApp.sharedPreferences.getString(KEY_USER_LOGIN, "")
        return WishDatabase
                .getInstance()
                .wishDao()
                .observeAll()
                .map {
                    it.filter { item ->
                        item.author.login != LocalWishesStore.UNKNOWN_USER && item.author.login == currentUser
                    }
                }
                .doOnSubscribe { loadWishesFromServer() }
    }

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_my))

    private fun loadWishesFromServer() {
        RetrofitStore.service.getWishList()
                .subscribeOn(Schedulers.io())
                .flatMap { response ->
                    val items = response.data.map {
                        Wish(
                                id = it.title.hashCode().toLong() + it.description.hashCode() + it.userId.hashCode(),
                                title = it.title,
                                description = it.description,
                                author = User(WishApp.sharedPreferences.getString(KEY_USER_LOGIN, "")!!),
                                photoId = 0
                        )
                    }
                    WishDatabase.getInstance().wishDao()
                            .insertAll(items)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ count ->
                    Log.d("loadWishes onSuccess", "Inserted count=$count")
                }, {
                    Log.e("loadWishes onSuccess", it.localizedMessage, it)
                }
                ).addTo(compositeDisposable)
    }

}