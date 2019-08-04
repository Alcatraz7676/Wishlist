package ru.ktsstudio.wishlist.ui.main.wishtabs

import io.reactivex.Observable
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.WishApp
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.utils.KEY_USER_LOGIN

class PopularFragment : WishFragment() {

    override fun getWishes(): Observable<List<WishAdapterModel.Wish>> {
        return WishDatabase
                .getInstance()
                .wishDao()
                .observeAll()
                .map {
                    it.filter { item ->
                        item.author.login != WishApp.sharedPreferences.getString(KEY_USER_LOGIN, "")
                    }
                }
    }

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_popular))

}