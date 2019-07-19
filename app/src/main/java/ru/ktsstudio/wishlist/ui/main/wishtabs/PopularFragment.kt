package ru.ktsstudio.wishlist.ui.main.wishtabs

import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.app.MainActivity

class PopularFragment : WishFragment() {

    override fun getWishes(): List<WishAdapterModel> {
        val header = Header(resources.getString(R.string.wishtabs_fragment_tv_header_popular))
        val wishes = (activity as MainActivity)
            .getAllWishes()
            .toMutableList()
        wishes.add(0, header)
        return wishes
    }

    companion object {
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }

}