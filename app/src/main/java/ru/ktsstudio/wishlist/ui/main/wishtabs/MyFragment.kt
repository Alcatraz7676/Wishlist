package ru.ktsstudio.wishlist.ui.main.wishtabs

import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.data.stores.LocalWishesStore
import ru.ktsstudio.wishlist.ui.app.MainActivity

class MyFragment : WishFragment() {

    override fun getWishes(): List<WishAdapterModel> {
        val header = Header(resources.getString(R.string.wishtabs_fragment_tv_header_my))
        val wishes = LocalWishesStore
            .getAllWishes()
            .filter { (it as Wish).author == (activity as MainActivity).currentUser }
        return listOf(header) + wishes
    }

}