package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.app.MainActivity

class MyFragment : WishFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(rv, getMyWishes())
    }

    private fun getMyWishes(): List<WishAdapterModel> {
        val header = Header(resources.getString(R.string.wishtabs_fragment_tv_header_my))
        val mainActivity = (activity as MainActivity)
        val wishes = mainActivity
            .getWishes()
            .filter { (it as Wish).author == mainActivity.currentUser }
            .toMutableList()
        wishes.add(0, header)
        return wishes
    }

    companion object {
        fun newInstance(): MyFragment {
            return MyFragment()
        }
    }

}