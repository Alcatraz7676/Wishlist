package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    private val header: Header
    private val wishAdapterDelegate: WishAdapterDelegate

    constructor(
        header: Header,
        clickListener: (WishAdapterModel.Wish) -> Unit,
        addToFavouriteListener: (Long, Boolean) -> Unit
    ) : super(WishDiffCallback()) {

        wishAdapterDelegate = WishAdapterDelegate(clickListener, addToFavouriteListener)

        sequenceOf(
            HeaderAdapterDelegate(),
            wishAdapterDelegate
        ).forEach { delegatesManager.addDelegate(it) }

        this.header = header
        items = mutableListOf()
    }

    fun setContactNames(contactNames: List<String>) {
        wishAdapterDelegate.contactNames = contactNames
    }

    fun getWishItems(): MutableList<WishAdapterModel.Wish> {
        return items.filterIsInstance(WishAdapterModel.Wish::class.java).toMutableList()
    }

    fun setWishItems(newWishes: List<WishAdapterModel.Wish>?) {
        val newItems = mutableListOf<WishAdapterModel>()
        if (newWishes != null && newWishes.isNotEmpty()) {
            newItems.addAll(newWishes)
            newItems.add(0, header)
        }
        items = newItems
    }
}