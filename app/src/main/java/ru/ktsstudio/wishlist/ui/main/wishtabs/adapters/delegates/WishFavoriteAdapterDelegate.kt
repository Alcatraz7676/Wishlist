package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_wish_favorite.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish

class WishFavoriteAdapterDelegate :
    AbsListItemAdapterDelegate<Wish, WishAdapterModel, WishFavoriteAdapterDelegate.WishFavoriteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): WishFavoriteHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_wish_favorite, parent, false)

        return WishFavoriteHolder(view)
    }

    override fun isForViewType(item: WishAdapterModel, items: MutableList<WishAdapterModel>, position: Int): Boolean {
        return item is Wish && item.isFavorite
    }

    override fun onBindViewHolder(item: Wish, holder: WishFavoriteHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class WishFavoriteHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(wish: Wish) {
            tv_title.text = wish.title
            tv_description.text = wish.description
            tv_author.text = wish.author.login
        }
    }

}