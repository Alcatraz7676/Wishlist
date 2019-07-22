package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishFavoriteAdapterDelegate
import ru.ktsstudio.wishlist.utils.diff.WishDiffCallback

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    private var emptyView: View? = null

    constructor(items: List<WishAdapterModel>, emptyView: View?) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate(),
            WishFavoriteAdapterDelegate()
        ).forEach { delegatesManager.addDelegate(it) }

        registerAdapterDataObserver(observer)

        this.emptyView = emptyView
        setItems(items)
    }

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    private fun checkIfEmpty() {
        if (emptyView != null) {
            val emptyViewGone = items.none { it is WishAdapterModel.Wish }
            Log.i("mytag", emptyViewGone.toString())
            emptyView!!.visibility = if (emptyViewGone) VISIBLE else GONE
        }
    }

}