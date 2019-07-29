package ru.ktsstudio.wishlist.ui.main.wishtabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import kotlinx.android.synthetic.main.fragment_rv.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.BaseFragment
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapter
import ru.ktsstudio.wishlist.utils.addTo

abstract class WishFragment : BaseFragment() {

    private val wishTabsNavigator: WishTabsNavigator
        get() = parentFragment as WishTabsNavigator

    protected lateinit var adapter: WishAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("WishFragment", "onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        adapter = WishAdapter(getWishes())
        initList(adapter)
        initSwipeRefreshLayout(adapter)
    }

    override fun onDestroyView() {
        Log.i("WishFragment", "onDestroyView()")
        super.onDestroyView()
    }

    private fun initList(adapter: WishAdapter) {
        with(recycler_view) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
        adapter.clickEvent
            .subscribe{
                wishTabsNavigator.navigateToWishDetail(it)
            }.addTo(compositeDisposable)
    }

    private fun initSwipeRefreshLayout(adapter: WishAdapter) {
        with(swipeContainer) {
            setColorSchemeResources(R.color.colorAccent)
            refreshes().subscribe {
                adapter.items = getWishes()
                swipeContainer.isRefreshing = false
            }.addTo(compositeDisposable)
        }
    }

    abstract fun getWishes(): List<WishAdapterModel>

}