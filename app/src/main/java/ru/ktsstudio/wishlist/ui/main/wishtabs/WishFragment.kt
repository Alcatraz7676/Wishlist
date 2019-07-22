package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter

abstract class WishFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WishAdapter(getWishes(), tv_empty_list)
        setupList(adapter)
        setupSwipeRefreshLayout(adapter)
    }

    private fun setupList(adapter: WishAdapter) {
        with(recycler_view) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun setupSwipeRefreshLayout(adapter: WishAdapter) {
        with(swipeContainer) {
            setColorSchemeResources(R.color.colorAccent)
            setOnRefreshListener {
                adapter.items = getWishes()
                swipeContainer.isRefreshing = false
            }
        }
    }

    abstract fun getWishes(): List<WishAdapterModel>

}