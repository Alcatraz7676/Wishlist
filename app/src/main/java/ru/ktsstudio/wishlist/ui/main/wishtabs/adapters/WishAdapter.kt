package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishFavoriteAdapterDelegate
import ru.ktsstudio.wishlist.utils.diff.WishDiffCallback

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    constructor(items: List<WishAdapterModel>) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate(),
            WishFavoriteAdapterDelegate()
        ).forEach { delegatesManager.addDelegate(it) }

        setItems(items)
    }

}