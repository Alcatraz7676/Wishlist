package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.HeaderAdapterDelegate
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates.WishAdapterDelegate

class WishAdapter : AsyncListDifferDelegationAdapter<WishAdapterModel> {

    private val clickSubject = PublishSubject.create<Wish>()
    val clickEvent : Observable<Wish> = clickSubject

    constructor(items: List<WishAdapterModel>) : super(WishDiffCallback()) {
        sequenceOf(
            HeaderAdapterDelegate(),
            WishAdapterDelegate(clickSubject)
        ).forEach { delegatesManager.addDelegate(it) }

        setItems(items)
    }

}