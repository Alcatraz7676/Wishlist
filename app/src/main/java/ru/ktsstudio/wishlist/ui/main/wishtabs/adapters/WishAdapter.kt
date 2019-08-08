package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Header
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    private val header: Header

    constructor(
        header: Header,
        contactNames: List<String>?,
        clickListener: (Wish) -> Unit
    ) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate(clickListener, contactNames)
        ).forEach { delegatesManager.addDelegate(it) }

        this.header = header
        items = mutableListOf()
    }

    override fun getItems(): MutableList<WishAdapterModel> {
        return super.getItems().filterIsInstance(Wish::class.java).toMutableList()
    }

    override fun setItems(items: MutableList<WishAdapterModel>?) {
        items?.add(0, header)
        super.setItems(items)
    }
}