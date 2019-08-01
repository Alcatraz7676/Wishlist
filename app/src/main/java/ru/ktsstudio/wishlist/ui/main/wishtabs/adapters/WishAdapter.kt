package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    constructor(items: List<WishAdapterModel>, clickListener: (Wish) -> Unit) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate(clickListener)
        ).forEach { delegatesManager.addDelegate(it) }

        setItems(items)
    }

}