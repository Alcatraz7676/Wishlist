package ru.ktsstudio.wishlist.ui.main.wishtabs

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter

abstract class WishFragment : Fragment() {

    fun setupRecyclerView() {
        val adapter = WishAdapter(getWishes())
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(activity)
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rv.setHasFixedSize(true)
        swipeContainer.setColorSchemeResources(R.color.colorAccent)
        swipeContainer.setOnRefreshListener {
            adapter.items = getWishes()
            swipeContainer.isRefreshing = false
        }
    }

    abstract fun getWishes(): List<WishAdapterModel>

}