package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.popular

import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.TabFragment

class PopularTabFragment : TabFragment() {

    override fun getWishes() = wishRepository.observePopularWishes()

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_popular))

}