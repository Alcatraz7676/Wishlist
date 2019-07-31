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

    protected val wishAdapter: WishAdapter by lazy {
        WishAdapter(getWishes())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSwipeRefreshLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler_view.adapter = null
    }

    private fun initList() {
        with(recycler_view) {
            adapter = wishAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
        wishAdapter.clickEvent
            .subscribe{
                wishTabsNavigator.navigateToWishDetail(it)
            }.addTo(compositeDisposable)
    }

    private fun initSwipeRefreshLayout() {
        with(swipeContainer) {
            setColorSchemeResources(R.color.colorAccent)
            refreshes().subscribe {
                wishAdapter.items = getWishes()
                swipeContainer.isRefreshing = false
            }.addTo(compositeDisposable)
        }
    }

    abstract fun getWishes(): List<WishAdapterModel>

}