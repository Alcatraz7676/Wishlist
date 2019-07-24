package ru.ktsstudio.wishlist.ui.main.wishtabs.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_wish.*
import ru.ktsstudio.wishlist.R
import ru.ktsstudio.wishlist.data.models.WishAdapterModel
import ru.ktsstudio.wishlist.data.models.WishAdapterModel.Wish
import ru.ktsstudio.wishlist.utils.IMAGE_PLACEHOLDER_URL
import ru.ktsstudio.wishlist.utils.ProgressPlaceholder

class WishAdapterDelegate :
    AbsListItemAdapterDelegate<Wish, WishAdapterModel, WishAdapterDelegate.WishHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): WishHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_wish, parent, false)

        return WishHolder(view)
    }

    override fun isForViewType(item: WishAdapterModel, items: MutableList<WishAdapterModel>, position: Int): Boolean {
        return item is Wish
    }

    override fun onBindViewHolder(item: Wish, holder: WishHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class WishHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        fun bind(wish: Wish) {
            tv_title.text = wish.title
            tv_description.text = wish.description
            if (wish.author != null && wish.author.login.isNotBlank())
                tv_author.text = wish.author.login
            else
                tv_author.isVisible = true
            if (wish.isFavourite)
                iv_favorite.isVisible = true
            if (wish.photoId != null) {
                iv_wish.isVisible = true
                Glide.with(iv_wish)
                    .load("$IMAGE_PLACEHOLDER_URL/${wish.photoId}")
                    .placeholder(ProgressPlaceholder(containerView.context))
                    .error(R.drawable.bg_placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade(factory))
                    .into(iv_wish)
            }
        }

    }


}