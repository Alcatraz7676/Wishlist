package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.app.MainActivity

class PopularFragment : WishFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

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