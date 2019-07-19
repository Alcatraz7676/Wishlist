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
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter

abstract class WishFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
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