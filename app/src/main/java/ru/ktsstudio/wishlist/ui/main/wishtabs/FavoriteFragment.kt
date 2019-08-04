package ru.ktsstudio.wishlist.ui.main.wishtabs

import io.reactivex.Observable
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.db.WishDatabase
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header

class FavoriteFragment : WishFragment() {

    override fun getWishes(): Observable<List<Wish>> {
        return WishDatabase
                .getInstance()
                .wishDao()
                .observeAll()
                .map {
                    it.filter { item -> item.isFavourite }
                }
    }

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_favorite))

}