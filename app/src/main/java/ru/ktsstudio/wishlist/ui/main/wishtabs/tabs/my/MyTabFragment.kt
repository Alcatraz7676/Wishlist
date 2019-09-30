package ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.my

import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.tabs.TabFragment

class MyTabFragment : TabFragment() {

    override fun getWishes() = tabInteractor.observeMyWishes()

    override fun getHeader() = Header(resources.getString(R.string.wishtabs_fragment_tv_header_my))

}