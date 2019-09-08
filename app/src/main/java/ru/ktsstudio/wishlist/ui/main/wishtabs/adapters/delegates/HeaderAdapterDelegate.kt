package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_header.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel
import ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.WishAdapterModel.Header

class HeaderAdapterDelegate :
    AbsListItemAdapterDelegate<Header, WishAdapterModel, HeaderAdapterDelegate.HeaderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): HeaderHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_header, parent, false)

        return HeaderHolder(view)
    }

    override fun isForViewType(
        item: WishAdapterModel,
        items: MutableList<WishAdapterModel>,
        position: Int
    ): Boolean {
        return item is Header
    }

    override fun onBindViewHolder(item: Header, holder: HeaderHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class HeaderHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(header: Header) {
            tv_title.text = header.title
        }
    }

}