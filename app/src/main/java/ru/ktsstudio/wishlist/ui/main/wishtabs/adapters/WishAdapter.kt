package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    constructor(items: List<WishAdapterModel>) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate()
        ).forEach { delegatesManager.addDelegate(it) }

        setItems(items)
    }

}