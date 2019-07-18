package ru.ktsstudio.wishlist.ui.main.wishtabs

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter

abstract class WishFragment : Fragment() {

    fun setupRecyclerView(rv: RecyclerView, items: List<WishAdapterModel>) {
        rv.adapter = WishAdapter(items)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rv.setHasFixedSize(true)
    }

}