package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_wish.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.models.WishAdapterModel
import ru.ktsstudio.wishlist.models.WishAdapterModel.Wish

class WishAdapterDelegate :
    AbsListItemAdapterDelegate<Wish, WishAdapterModel, WishAdapterDelegate.WishHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): WishHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_wish, parent, false)

        return WishHolder(view)
    }

    override fun isForViewType(item: WishAdapterModel, items: MutableList<WishAdapterModel>, position: Int): Boolean {
        return item is Wish && !item.isFavorite
    }

    override fun onBindViewHolder(item: Wish, holder: WishHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class WishHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(wish: Wish) {
            tv_title.text = wish.title
            tv_description.text = wish.description
            tv_author.text = wish.author.login
        }
    }

}