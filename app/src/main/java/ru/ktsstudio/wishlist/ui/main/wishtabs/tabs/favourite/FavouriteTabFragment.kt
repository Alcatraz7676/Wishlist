package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.favourite

import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.TabFragment

class FavouriteTabFragment : TabFragment() {

    override fun getWishes() = wishRepository.observeFavouriteWishes()

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_favorite))

}